node {
    def projectID = "construction-project-382718"
    def imageName = "construction-service"
    def tag = "latest"
    def mvnHom = tool name: 'maven-3', type: 'maven'
    def kubeconfigPath = "config"
    def deploymentYaml = "deployment-dev.yaml"
    def clusterName ="onlineboutique"
    def location ="us-central1"
    def credentialsId="k8s-service-cred"
    //def region = "us-central1"
    //def repositoryName = "construction-service"
    stage('CHECKOUT') {
            checkout scm
    }
     stage('BUILD') {
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn clean validate compile package"
        }
    }
     stage('UNIT TEST') {
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn test"
        }
     }
    stage('INTEGRATION TEST') {
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn verify"
        }
     }
    stage('CODE QUALITY') {
        withSonarQubeEnv(credentialsId:'sonar-auth') {
            sh "${mvnHom}/bin/mvn clean package sonar:sonar"
        }
    }


    stage('BUILD AND PUSH IMAGE TO ARTIFACT CONTAINER') {
        withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]) {
            sh "gcloud auth activate-service-account --key-file=${GC_KEY}"
            projectId = "construction-project-382718"
            registry = "construction-docker-repo"
            imageName = "construction-service"
            tag = "${env.BUILD_NUMBER}"
            sh "docker build -t gcr.io/${projectId}/${imageName}:${tag} ."
            sh "gcloud docker -- push gcr.io/${projectId}/${imageName}:${tag}"
            sh "gcloud docker -- push gcr.io/${projectId}/${imageName}:latest"
        }
    }
    stage('Deploy to K8s') {
      steps {
        sh 'ls -ltr'
        sh 'pwd'
        step([$class: 'KubernetesEngineBuilder', projectId: "${projectId}", clusterName: "${clusterName}", location: "${location}", manifestPattern: 'deployment-dev.yaml', credentialsId: "${credentialsId}", verifyDeployments: true])
      }
    }
   /* container('google-cloud-sdk') {
         withCredentials([googleServiceAccount(credentialsId: '${credentialsId}', jsonKeyVariable: 'GCLOUD_KEY_FILE')]) {
           sh '''
             gcloud auth activate-service-account --key-file=${}GCLOUD_KEY_FILE}
             gcloud container clusters get-credentials ${clusterName} --zone=${location} --project=${projectId}
             kubectl apply -f deployment-dev.yaml --validate=false
           '''
         }
       } */

    /* stage('Deploy to Kubernetes') {
          withCredentials([file(credentialsId: 'kubeconfig1', variable: 'KUBECONFIG', useExisting: true, filenameVariable: 'kubeconfigPath')]) {
                      // Apply deployment YAML to Kubernetes cluster
                      sh "kubectl apply -f ${deploymentYaml} --kubeconfig='${kubeconfigPath}'"
                  }
                  } */
     /* stage('Deploy to Kubernetes') {
             withEnv(['KUBECONFIG=' + kubeconfigPath]) {
                 sh "kubectl apply -f deployment-dev.yaml"
             }
         } */


}
