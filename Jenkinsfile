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

        // Stage 3 : Publish the artifacts to nexus

        stage('Publish to Nexus') {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'VinayDevOpsLab', classifier: '', file: 'target/VinayDevOpsLab-0.0.11-SNAPSHOT.war', type: 'WAR']], credentialsId: 'de70796e-5f33-4e8b-8ea6-b996f84114f7', groupId: 'com.vinaysdevopslab', nexusUrl: '3.239.48.22:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'KKDevOpsLab-SNAPSHOT', version: '0.0.11-SNAPSHOT'
            }
        }

        // Stage 4 : Print some information
        stage('Print Environment variables') {
                    steps {
                        echo "Artifact ID is '${ArtifactId}'"
                        echo "Version is '${Version}'"
                        echo "GroupID is '${GroupId}'"
                        echo "Name is '${Name}'"
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
