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
                script {

                    def NexusRepo = Version.endsWith("SNAPSHOT") ? "KKDevOpsLab-SNAPSHOT" : "KKDevOpsLab-RELEASE"

                    nexusArtifactUploader artifacts:
                    [[artifactId: "${ArtifactId}",
                    classifier: '',
                    file: "target/"${Name}"-"${Version}".war",
                    type: 'WAR']],
                    credentialsId: 'de70796e-5f33-4e8b-8ea6-b996f84114f7',
                    groupId: "${GroupID}",
                    nexusUrl: '3.235.185.222:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: "${NexusRepo}",
                    version: "${Version}"
                }
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
