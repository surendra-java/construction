/*
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
   stage('test'){


                withCredentials([file(credentialsId: 'gcloud-cred', variable: 'GCLOUD_CRED')]) {
                    sh '''
                    gcloud version
                    gcloud auth activate-service-account --key-file="$GCLOUD_CRED"
                    gcloud compute zones list
                    '''
                }
        }
    */
/*  stage('docker'){

        def GCLOUD_PATH = tool name: 'gcloud', type: 'google-cloud-sdk'
         sh '$GCLOUD_PATH/bin --version'
            withCredentials([file(credentialsId: 'construction-project', variable: 'GC_KEY')]) {
                sh "gcloud auth activate-service-account --key-file=${GC_KEY}"

                def projectId = "construction-project-382718"
                def registry = "us-central1-construction-project-382718"

                def image = "gcr.io/${registry}/construction-service:${env.BUILD_NUMBER}"

                sh "docker build -t ${image} ."
                sh "docker push ${image}"
            }
    } *//*



}
 */
pipeline {
    agent any
    stages{
        stage('test'){
            steps {
                    withCredentials([file(credentialsId: 'gcloud-cred', variable: 'GCLOUD_CRED')]) {
                    sh '''
                    gcloud version
                    gcloud auth activate-service-account --key-file="$GCLOUD_CRED"
                    gcloud compute zones list
                    '''
                }
            }
        }
    }
}