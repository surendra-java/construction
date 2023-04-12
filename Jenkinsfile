def clusterName = "construction-autopilot-cluster"

def clusterExists() {
    def cmd = "gcloud container clusters list --format 'value(name)' --filter 'name=${clusterName}'"
    return sh(
        script: cmd,
        returnStdout: true
    ).trim() == clusterName
}
pipeline {
    agent any
    environment {
        projectID = "construction-project-382718"
        imageName = "construction-service"
        tag = "latest"
        mvnHom = tool name: 'maven-3', type: 'maven'
        kubeconfigPath = "config"
        deploymentYaml = "deployment-dev.yaml"
        clusterName = "onlineboutique"
        location = "us-central1"
        credentialsId = "k8s-service-cred"
    }

    stages {
        stage('CHECKOUT') {
            steps {
                checkout scm
            }
        }

        stage('BUILD') {
            steps {
                withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
                    sh "${mvnHom}/bin/mvn clean validate compile package"
                }
            }
        }

        stage('UNIT TEST') {
            steps {
                withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
                    sh "${mvnHom}/bin/mvn test"
                }
            }
        }

        stage('INTEGRATION TEST') {
            steps {
                withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
                    sh "${mvnHom}/bin/mvn verify"
                }
            }
        }

        stage('CODE QUALITY') {
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-auth', installationName: 'SonarQube') {
                    sh "${mvnHom}/bin/mvn clean package sonar:sonar"
                }
            }
        }

        stage('BUILD AND PUSH IMAGE TO ARTIFACT CONTAINER') {
            steps {
                withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]) {
                    sh "gcloud auth activate-service-account --key-file=${GC_KEY}"
                    sh "docker build -t gcr.io/${projectID}/${imageName}:${tag} ."
                    sh "gcloud auth configure-docker"
                    sh "gcloud docker -- push gcr.io/${projectID}/${imageName}:${tag}"
                    sh "gcloud docker -- push gcr.io/${projectID}/${imageName}:latest"
                }
            }
        }

        /* stage('Deploy to K8s') {
            steps {
                sh 'ls -ltr'
                sh 'pwd'
                step([$class: 'KubernetesEngineBuilder', projectId: env.projectID, clusterName: env.clusterName, location: env.location, manifestPattern: 'deployment-dev.yaml', credentialsId: env.credentialsId, verifyDeployments: true])
            }
        } */
        stage('Create autopilot cluster') {
                    when {
                        expression { !clusterExists() }
                    }
                    steps {
                        // Configure GCP credentials
                        withCredentials([googleServiceAccountKey(credentialsId: 'jenkins-sa-key', jsonKeyVariable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                            // Authenticate with GCP
                            sh "gcloud auth activate-service-account --key-file=${GOOGLE_APPLICATION_CREDENTIALS}"

                            // Create the autopilot cluster
                            sh "gcloud container clusters create ${clusterName} --release-channel regular --num-nodes 1 --enable-autopilot --region us-central1-a --machine-type e2-medium --disk-size 100 --image-type cos_containerd --service-account jenkins-sa@${projectID}.iam.gserviceaccount.com"
                        }
                    }
                }
    }
}
