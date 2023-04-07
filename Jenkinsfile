node {
    def app
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
    stage('checkout'){
        checkout scm
    }
    stage('build'){
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]){
            def mvnHome = tool name: 'maven', type: 'maven'
            sh "${mvnHome}/bin/mvn package"
        }
    }

    node(label){
          stage('Test -  Execution of gcloud command') {
           container('gcloud') {
                  sh "gcloud compute zones --help"
           }
       }
    }
}
