node {
    environment {
        PROJECT_ID = "construction-project-382718"
        IMAGE_NAME = "construction-service"
        TAG = "latest"
    }
    stage('Build') {
        def mvnHom = tool name: 'maven-3', type: 'maven'
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn package"
        }
    }

    stage('Build Image') {
        sh "docker build -t ${IMAGE_NAME}:${TAG} ."
    }

    stage('Push') {
        withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]) {
            sh """
                echo \$GC_KEY > key.json
                gcloud auth activate-service-account --key-file=key.json
                gcloud config set project ${PROJECT_ID}
                docker tag ${IMAGE_NAME}:${TAG} gcr.io/${PROJECT_ID}/${IMAGE_NAME}:${TAG}
                docker push gcr.io/${PROJECT_ID}/${IMAGE_NAME}:${TAG}
            """
        }
    }
}
