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
        withCredentials([file(credentialsId: 'construction-project', variable: 'GC_KEY')]) {
            sh """
                apt-get update && apt-get install -y gnupg curl
                export CLOUD_SDK_REPO="cloud-sdk-$(lsb_release -c -s)"
                echo "deb http://packages.cloud.google.com/apt $CLOUD_SDK_REPO main" | tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
                curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
                apt-get update && apt-get install -y google-cloud-sdk
                gcloud auth activate-service-account --key-file=${GC_KEY}
                export projectId="construction-project-382718"
                export registry="us-central1-construction-project-382718"
                export image="gcr.io/${registry}/construction-service:${env.BUILD_NUMBER}"
                docker build -t ${image} .
                docker push ${image}
            """
        }
    }


}
