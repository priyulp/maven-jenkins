pipeline {
    //Directives
    agent any
    tools {
        maven 'Maven'
    }

    environment {
        ArtifactId = readMavenPom().getArtifactId()
        Version = readMavenPom().getVersion()
        Name = readMavenPom().getName()
        GroupId = readMavenPom().getGroupId()
    }

    stages {
        // Specify various stage with in stages

        // stage 1. Build
        stage('Build') {
            steps {
                sh 'mvn clean install package'
            }
        }

        // Stage2 : Testing
        stage('Test') {
            steps {
                echo ' testing......'
            }
        }

        // Stage2 : Testing
        stage('Sonarqube Analysis') {
            steps {
                echo ' source code published to Sonarqube for Static code analysis......'
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }

        // Stage 3 : Publish the artifacts to nexus

        stage('Publish to Nexus') {
            steps {
                script {
                    def NexusRepo = Version.endsWith('SNAPSHOT') ? 'KKDevOpsLab-SNAPSHOT' : 'KKDevOpsLab-RELEASE'
                    nexusArtifactUploader artifacts:
                 [[artifactId: "${ArtifactId}",
                  classifier: '',
                   file: "target/${Name}-${Version}.war",
                    type: 'war']],
                     credentialsId: 'd870f477-b1a0-4dc2-a28b-a5995d9f75b6',
                      groupId: "${GroupId}",
                       nexusUrl: '34.239.169.62:8081',
                        nexusVersion: 'nexus3',
                         protocol: 'http',
                          repository: "${NexusRepo}",
                           version: "${Version}"
                }
            }
        }

        // Stage 5 : Publish the source code to Tomcat
        stage('Deploy to tomcat') {
            steps {
                echo ' deploying.....'
                sshPublisher(publishers:
                [sshPublisherDesc(
                    configName: 'Ansible_Controller',
                    transfers: [
                        sshTransfer(
                            cleanRemote: false,
                            execCommand:
                            'ansible-playbook /opt/playbooks/downloadanddeploy_as_tomcat_user.yaml -i /opt/playbooks/hosts',
                            execTimeout: 12000
                        )
                    ],
                    usePromotionTimestamp: false,
                    useWorkspaceInPromotion: false,
                    verbose: false)
                    ])
            }
        }
        // Stage 6 : Deploying the build artifact to Docker tomcat
        stage('Deploy to Docker') {
            steps {
                echo ' deploying to docker.....'
                sshPublisher(publishers:
            [sshPublisherDesc(
                configName: 'Ansible_Controller',
                transfers: [
                    sshTransfer(
                        cleanRemote: false,
                        execCommand:
                        'ansible-playbook /opt/playbooks/downloadanddeploy_docker.yaml -i /opt/playbooks/hosts',
                        execTimeout: 12000
                    )
                ],
                usePromotionTimestamp: false,
                useWorkspaceInPromotion: false,
                verbose: false)
                ])
            }
        }
    }
}
