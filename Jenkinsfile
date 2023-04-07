pipeline {
  agent  any
    stages {
      stage('Test') {
        steps {  
          sh " echo Test is not avalaible"
        }
      }
    stage('Build and push image with Container Builder') {
      steps {
        withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]){
             sh "gcloud auth activate-service-account --key-file='$GC_KEY'"
         }
        }
      }
    }
 }
