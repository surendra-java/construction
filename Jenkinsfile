pipeline {
    agent any
    environment {
        M2_HOME = "C:/path/to/maven"
        PATH = "${M2_HOME}/bin:${PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
    }
}
