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
      withEnv(["path=${tool name: 'gcloud', type: 'gcloud-sdk'}"]){
            withCredentials([file(credentialsId: 'gcr-cred', variable: 'SERVICE_ACCOUNT_KEY')]) {
                      sh "gcloud auth activate-service-account --key-file=${SERVICE_ACCOUNT_KEY}"
                      sh "gcloud auth configure-docker --quiet"
                      sh "docker push gcr.io/construction-project-382718/construction:latest"
        }
        }
    }
}
