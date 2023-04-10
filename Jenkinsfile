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
   /*  stage('BUILD') {
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
    */

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
            kubeconfig(caCertificate: '''apiVersion: v1
            clusters:
            - cluster:
                certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUVMVENDQXBXZ0F3SUJBZ0lSQUxCclRiSlJsbjhZRGZSWDMzbVRHRkF3RFFZSktvWklodmNOQVFFTEJRQXcKTHpFdE1Dc0dBMVVFQXhNa09UbG1OakF5TWpBdE1XRXhOQzAwWkdKa0xUZ3pZemd0WWpsak5qWXlabUU1TUdNMwpNQ0FYRFRJek1EUXhNREE1TkRRMU1Wb1lEekl3TlRNd05EQXlNVEEwTkRVeFdqQXZNUzB3S3dZRFZRUURFeVE1Ck9XWTJNREl5TUMweFlURTBMVFJrWW1RdE9ETmpPQzFpT1dNMk5qSm1ZVGt3WXpjd2dnR2lNQTBHQ1NxR1NJYjMKRFFFQkFRVUFBNElCandBd2dnR0tBb0lCZ1FEaXBJTHdVRTBkUWVtL1lCR05KSmR3WkltN1NNN3Y5dWt3YTZ3MwpKQjlqbzFCU1ZtaXRpS1BXbnczM1NRd2dqKzg5WGFiNlFsUi8wRE1vaG1CR3NIVm5SZnZyNTZTeFRjQklYSS9xCnRBMUhhbWVORmxxNGhJTW13Qk9GMnpaYXlVV1JQLzZibllHN1kwQkYvdldqWmJBL1hvY1ROdzlWS0pPdUlZbDEKb0crQ0xYNHEwdnJEWkhFdFUzOTNxNklsMU9KdUpPUjhNZ083N3dkb3ZsbC9ZTzFBaExJY3RPM01jSU1DZDdQbQpUc3dadWRaRlBOczFuVHU2aGxrdjFLRFZSc0V3TU5pRWNPNjB4VjF6bTJTb25NdXRHT0lvbHQyTFZrVzZLQmdUCnRBQUdreUFQWndhTkVLd2dRTFhuc1hHZm0zSC9UTWdNMzhCSTUxUUYySGVFVmNvb0crV2pwbnhoNW5xMjFoRlIKaFFDYng0UXBKdEU2TDkva2V6MkxIckVmdHZoYnEzRi9xWGFoUldFd2oxK0JhT1ZzRWVsMEkvcCtjT294MnRFLwo3OXVyNzZqdS9KMS9GcjkwZ3JZNTFZUXowT1pyZTV3S0FldWhDdlZTRFJqVGc5ZWZVd3VKVEhGNHljK3RDbStTClBKREhSeVYyYjV1aW9sOEc2YkZDcGttRFlEOENBd0VBQWFOQ01FQXdEZ1lEVlIwUEFRSC9CQVFEQWdJRU1BOEcKQTFVZEV3RUIvd1FGTUFNQkFmOHdIUVlEVlIwT0JCWUVGTjR4b0hYZ21GZ0dlWlg2VFdwY3RpQTZobngxTUEwRwpDU3FHU0liM0RRRUJDd1VBQTRJQmdRQlVFYVc4K1MwM1luU1lST0tWR01lMHdndkR6QURUR1RaLzFiMkNCcm1BCkN5UWQwWlFBRmZxTjR5SkE4Q3QrTGMyd0crTE8wQURGYmRVZEVqOCtkM0k3V2ZneDZmTnVieTY5OWxTZDBZQy8KVndRY1h4T0pvdU9ET0NxTWl4TXNkVTF2K3pVTHoyUEVqQnJGdWt2cHRHcStmNlRuOG1FUmlKOTB3MkFpWGlWeQpsWjdoNHlxek9LTFlSanJGQUdNMVBKdGJ1Q0p2bU1MUERwdnBsRkw0Z21QdXd0dW1Xc2l5TlJYWWRUZEtjRngrCjByeThiOXdLMldMdG5IUUJOV3lYaVplV2Q3L1hqblgxbDh0MDY2cU9md0MvK0U5NFJtNjZlOE5hVGpua0dpQ08KbFNCdDdiVDZZcTgrYXJvQ1VrdXlTUVJ0WlhENDhSQk5LWVBqN3pLSUhGd3A5ZEdaR2RsVUtSOW9HTlpLd3JPUAppY2ZiT2tBeTUvRnlneExzZzJSMVJuV2k1L3FOaGJ6Y0d2VXFYVGplSDlnT2RZa3pNS3ZPSUlwRjlDMy96S0s1CktKREtwdFdObFAwOWtYdUVDMDloUTRBMGpnZmNha0ZxREVNVGlLNm5idStpZWtYdC9KNnRKNWV3SFdjN1VqSjcKQXpmdUhPVDN3N0FKNFBZckI5KzFDTnM9Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K
                server: https://35.232.54.41
              name: gke_construction-project-382718_us-central1_construction-cluster
            contexts:
            - context:
                cluster: gke_construction-project-382718_us-central1_construction-cluster
                user: gke_construction-project-382718_us-central1_construction-cluster
              name: gke_construction-project-382718_us-central1_construction-cluster
            current-context: gke_construction-project-382718_us-central1_construction-cluster
            kind: Config
            preferences: {}
            users:
            - name: gke_construction-project-382718_us-central1_construction-cluster
              user:
                exec:
                  apiVersion: client.authentication.k8s.io/v1beta1
                  command: gke-gcloud-auth-plugin.exe
                  installHint: Install gke-gcloud-auth-plugin for use with kubectl by following
                    https://cloud.google.com/blog/products/containers-kubernetes/kubectl-auth-changes-in-gke
                  provideClusterInfo: true''', credentialsId: 'kubeconfig', serverUrl: 'https://35.232.54.41') {
                // some block
        }







    post {
        always {
            jacoco(execPattern: '**/target/jacoco.exec')
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}
