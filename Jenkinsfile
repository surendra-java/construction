node {
    def app
    stage('checkout'){
        checkout scm
    }
    stage('build'){
        withEnv(["JAVA_HOME=${tool name: 'java-11', type: 'jdk'}"]){
            def mvnHome = tool name: 'maven', type: 'maven'
            sh "${mvnHome}/bin/mvn package"
            sh "${mvnHome}/bin/mvn -s /path/to/settings.xml clean package deploy -Dmaven.deploy.skip=true -Dmaven.artifact.upload.single=true -Dmaven.repo.local=$WORKSPACE/.m2/repository -DaltDeploymentRepository=gcp-repository::default::https://us-central1-maven.pkg.dev/construction-project-382718/construction-private-repo/"
        }
    }
}