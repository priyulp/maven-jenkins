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
                nexusArtifactUploader artifacts: [[artifactId: 'VinayDevOpsLab', classifier: '', file: 'target/VinayDevOpsLab-0.0.11-SNAPSHOT.war', type: 'WAR']], credentialsId: 'de70796e-5f33-4e8b-8ea6-b996f84114f7', groupId: 'com.vinaysdevopslab', nexusUrl: '3.235.185.222:8081', nexusVersion: 'nexus2', protocol: 'http', repository: 'KKDevOpsLab-SNAPSHOT', version: '0.0.11-SNAPSHOT'
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
