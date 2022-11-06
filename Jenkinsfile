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
                    nexusArtifactUploader artifacts: [[artifactId: 'VinayDevOpsLab', classifier: '', file: 'target/VinayDevOpsLab-0.0.11-SNAPSHOT', type: 'WAR']], credentialsId: '', groupId: 'com.vinaysdevopslab', nexusUrl: '3.239.48.22:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'KKDevOpsLab-SNAPSHOT', version: '0.0.11-SNAPSHOT'
             }
        }

        // Stage 5 : Publish the source code to Sonarqube
        stage('Deploy') {
            steps {
                echo ' deploying.....'
            }
        }
    }
}
