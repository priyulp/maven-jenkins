pipeline {
    agent any

    environment {
        IMAGE_NAME = "yourdockerhubusername/mavenjenkins"
        KUBECONFIG_CREDENTIAL_ID = 'your-kubeconfig-cred-id'
        DOCKER_CREDS = credentials('your-dockerhub-creds-id')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'git@github.com:priyulp/mavenJenkins.git', credentialsId: 'your-git-creds-id'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def imageTag = "${IMAGE_NAME}:${BUILD_NUMBER}"
                    sh "docker build -t ${imageTag} ."
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDS}", passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push ${imageTag}"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withCredentials([file(credentialsId: "${KUBECONFIG_CREDENTIAL_ID}", variable: 'KUBECONFIG')]) {
                    script {
                        // Choose target (alternate)
                        def targetColor = sh(returnStdout: true, script: '''
                            CURRENT=$(kubectl get svc prod-service -o=jsonpath='{.spec.selector.app}')
                            if [ "$CURRENT" == "blue" ]; then echo "green"; else echo "blue"; fi
                        ''').trim()

                        def imageTag = "${IMAGE_NAME}:${BUILD_NUMBER}"
                        
                        sh """
                        sed 's|IMAGE_TAG|${imageTag}|' k8s/deployment-${targetColor}.yaml | kubectl apply -f -
                        kubectl patch svc prod-service -p '{"spec":{"selector":{"app":"${targetColor}"}}}'
                        """
                    }
                }
            }
        }
    }
}
