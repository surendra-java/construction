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
        withCredentials([file(credentialsId: 'construction-project', variable: 'construction-project')]) {
                    sh "gcloud auth activate-service-account --key-file=${construction-project}"

                    projectId = "construction-project-382718"
                    registry = "us-central1-construction-project-382718"

                    image = "gcr.io/${registry}/construction-service:${env.BUILD_NUMBER}"

                    sh "docker build -t ${image} ."
                    sh "docker push ${image}"
                }
    }
}
