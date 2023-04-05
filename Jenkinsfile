node {
    def app
    stage('checkout'){
        checkout scm
    }
    stage('build'){
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]){
            def mvnHome = tool name: 'maven', type: 'maven'
            sh "${mvnHome}/bin/mvn package"
        }
    }
    stage('docker'){
        withCredentials([string(credentialsId: 'construction-project', variable: 'GCP_CREDENTIALS_JSON')]) {
            sh "gcloud auth activate-service-account --key-file=<(echo '$GCP_CREDENTIALS_JSON')"

            def projectId = "construction-project-382718"
            def registry = "us-central1-construction-project-382718"

            def image = "gcr.io/${registry}/construction-service:${env.BUILD_NUMBER}"

            sh "docker build -t ${image} ."
            sh "docker push ${image}"
        }
    }
}
