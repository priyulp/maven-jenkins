pipeline {
    //Directives
    agent any
    tools {
        maven 'Maven'
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

        // Stage 3 : Publish the artifacts to nexus

        stage('Publish to Nexus') {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'VinayDevOpsLab', classifier: '', file: 'target/com.vinaysdevopslab-0.0.10', type: 'war']], credentialsId: 'daedaf3f-a3f9-454a-ae1e-46c50463f7d7', groupId: 'com.vinaysdevopslab', nexusUrl: '3.235.185.222', nexusVersion: 'nexus3', protocol: 'http', repository: 'KKDevOpsLab-SNAPSHOT', version: '0.0.11'
            }
        }

        // Stage4 : Publish the source code to Sonarqube
        stage('Deploy') {
            steps {
                echo ' deploying.....'
            }
        }
    }
}
