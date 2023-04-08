node {
    def projectID = "construction-project-382718"
    def imageName = "construction-service"
    def tag = "latest"
    stage('Build') {
        def mvnHom = tool name: 'maven-3', type: 'maven'
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn package"
        }
    }

    stage('Build and Push Image') {
        withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]) {
            sh "gcloud auth activate-service-account --key-file=${GC_KEY}"
            projectId = "construction-project-382718"
            registry = "construction-docker-repo"
            imageName = "construction-service"
            tag = "${env.BUILD_NUMBER}"
            sh "docker build -t gcr.io/${projectId}/${imageName}:${tag} ."
            sh "gcloud docker -- push gcr.io/${projectId}/${imageName}:${tag}"
        }
    }

}
