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
        withEnv(["PATH=${tool name: 'gcloud', type: 'google-cloud-sdk'}"]) {
            withCredentials([file(credentialsId: 'construction-project', variable: 'GC_KEY')]) {
                sh "gcloud auth activate-service-account --key-file=${GC_KEY}"

                def projectId = "construction-project-382718"
                def registry = "us-central1-construction-project-382718"

                def image = "gcr.io/${registry}/construction-service:${env.BUILD_NUMBER}"

                sh "docker build -t ${image} ."
                sh "docker push ${image}"
            }
        }
    }


}
