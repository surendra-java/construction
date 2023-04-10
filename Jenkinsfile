node {
    def projectID = "construction-project-382718"
    def imageName = "construction-service"
    def tag = "latest"
    def mvnHom = tool name: 'maven-3', type: 'maven'
    //def region = "us-central1"
    //def repositoryName = "construction-service"
    stage('CHECKOUT') {
            checkout scm
    }
    stage('BUILD') {
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]) {
            sh "${mvnHom}/bin/mvn package"
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
   /*  stage('Publish to Artifact Registry') {
        withCredentials([file(credentialsId: 'gcr-cred', variable: 'GC_KEY')]) {
            withEnv(['CLOUDSDK_AUTH_CREDENTIAL_FILE_OVERRIDE=${GC_KEY}']) {
                def mvnHome = tool name: 'maven-3', type: 'maven'
                def cmd = "${mvnHome}/bin/mvn deploy -s ${WORKSPACE}/settings.xml -Dmaven.test.skip=true"
                sh cmd
            }
        }
    } */

    stage('BUILD AND PUSH IMAGE TO ARTIFACT CONTAINER') {
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
    stage('Deploy to Kubernetes'){
        	try {
               		kubernetesDeploy(configs: "deployment.yaml", kubeconfigId: "kubeconfig")
               }catch (e) {
        			println e
    		   }

      }
    post {
        always {
            jacoco(execPattern: '**/target/jacoco.exec')
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}
