node {
    def app
    environment {
        PROJECT_ID = 'construction-project-382718'
        REGISTRY = 'gcr.io'
        IMAGE = 'construction'
        TAG = 'latest'
        //SERVICE_ACCOUNT_KEY = credentials('gcp-service-account-key')
      }
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
            withCredentials([string(credentialsId: 'gcr-cred', variable: 'SERVICE_ACCOUNT_KEY')]) {
                      sh "gcloud auth activate-service-account --key-file=${SERVICE_ACCOUNT_KEY}"
                      sh "gcloud auth configure-docker --quiet"
                      sh "docker push ${REGISTRY}/${PROJECT_ID}/${IMAGE}:${TAG}"
        }
    }
}
