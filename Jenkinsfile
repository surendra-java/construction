
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

        stage('DEPLOY ON CLUSTER') {
            steps {
                withCredentials([file(credentialsId: 'jenkins-sa-key', variable: 'GC_KEY')]) {
                    sh("gcloud auth activate-service-account --key-file=${GC_KEY}")
                    sh("gcloud version")
                    /* sh "gcloud container clusters create-auto construction-autopilot-cluster \
                            --project=construction-project-382718 --region=us-central1 \
                        	--service-account jenkins-sa@construction-project-382718.iam.gserviceaccount.com"
                    */
                    sh "kubectl apply -f deployment-dev.yaml"
                }
            }
        }
    }
}
