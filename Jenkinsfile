def project = 'my-project'
def appName = 'my_app-name'
def zone = 'us-east1-d'
def feSvcName = "${appName}"
def imageTag = "gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

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
        container('gcloud') {
          sh "cd .."
          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${imageTag} ."
        }
      }
    }
    stage('Deploy Development') {
      // Canary branch
      when { branch 'develop' }
      steps {
        container('kubectl') {
          sh ("echo BRANCH develop is not avalaible")

        }
      }
    }
    stage('Deploy Test') {
      // Canary branch
      when { branch 'develop' }
      steps {
        container('kubectl') {
          sh ("echo BRANCH develop is not avalaible")

        }
      }
    }
    stage('Deploy Production') {
      // Production branchh
      when { branch 'master' }
      steps {
        container('kubectl') {
          sh ("echo BRANCH Master is not avalaible")

        }
      }
    }

  }
}