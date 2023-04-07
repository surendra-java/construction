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
    def label = "gcloud-command-${UUID.randomUUID().toString()}"

    podTemplate(label: label, yaml: """
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: gcloud
        image: gcr.io/cloud-builders/gcloud
        command:
        - cat
        tty: true
    """
      )
    node(label){
                   stage('Test -  Execution of gcloud command') {
                     container('gcloud') {
                       sh "gcloud compute zones --help"
                     }
                     }
    /* stage('docker'){
      //withEnv(["path=${tool name: 'gcloud', type: 'gcloud-sdk'}"]){
            withCredentials([file(credentialsId: 'gcr-cred', variable: 'SERVICE_ACCOUNT_KEY')]) {
                      sh "gcloud auth activate-service-account --key-file=${SERVICE_ACCOUNT_KEY}"
                      sh "gcloud auth configure-docker --quiet"
                      sh "docker push gcr.io/construction-project-382718/construction:latest"
      //  }
        }
    } */
}
