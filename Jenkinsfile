node {
    def projectID = "construction-project-382718"
    def imageName = "construction-service"
    def tag = "latest"
    def mvnHom = tool name: 'maven-3', type: 'maven'
    def deploymentYaml = "deployment-dev.yaml"
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
    stage('Deploy to Kubernetes') {
          withCredentials([file(credentialsId: 'kubeconfig1', variable: 'KUBECONFIG', useExisting: true)]) {
                      // Apply deployment YAML to Kubernetes cluster
                      sh "kubectl apply -f ${deploymentYaml} --kubeconfig='apiVersion: v1
                                                                           clusters:
                                                                           - cluster:
                                                                               certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUVMRENDQXBTZ0F3SUJBZ0lRQlVwWWczQnZ2Y2xscTNxZkk1Y09WekFOQmdrcWhraUc5dzBCQVFzRkFEQXYKTVMwd0t3WURWUVFERXlSbE5UTmxZekU1TXkxa056a3pMVFEzTURjdFlXUXdNUzFrTVRNd1pUUmlaVEJrTldRdwpJQmNOTWpNd05ERXhNVEF3TlRReVdoZ1BNakExTXpBME1ETXhNVEExTkRKYU1DOHhMVEFyQmdOVkJBTVRKR1UxCk0yVmpNVGt6TFdRM09UTXRORGN3TnkxaFpEQXhMV1F4TXpCbE5HSmxNR1ExWkRDQ0FhSXdEUVlKS29aSWh2Y04KQVFFQkJRQURnZ0dQQURDQ0FZb0NnZ0dCQU5Cb2M0NTc1c0E3TzVxclpNcjJuc0MwY2o3czVvVWZkRGlLMGcwOApiWDBtQ2FOLytlZXNZcXZDUk9nRm5paWFlWXNTcE0zU1Aza2xJUkc3ZW9CdUwxMVZ3aWFhSDFPemVLNEZWRmprCjF6Q3lFVnMyUHlZQktsaFZ3MWJRYlVjZjBBUGFWamFBUDR5MFdqOWtEYXpLNkwrZzdzcXpKc0xpSzFKVzY0OXkKWHNKQkJIeUF6Q2h1M0dRMTZaellJd0lld0NuOHR0Q0JGZndKYUhlSzNBWFhZK0N3NHdJci9Jelg0YkFnSkovSwpydDF5MWR0QTg2NVY5MDZjWnVlYi92STBTOS84ZVA5WVRrOFFud1JnRTFOWkJ1aDl1UGRDa3dteGNiSmdkaUZVCjNNUG5rWU1mYjcySmJ6c1RkckljSmtTVktDUXI4UXJyYTU0RmxoNk5NbE9oRlNYclJ1cjl4VUFCa3NPeDRGWVMKQWljczlCM1NzYndVQzM2eFpsdUNWM3hEVmY2RUd2bHFHdVZYa25ZS2hzTFJkUHByT3VIZDdXR1pnZ0RkRkgwaApndHpiRGx2a29YZkRxZWZOOFprcnd3dlRaNmdlRDgvYVErVXJsNjdxaDdtMkI0NnpYRmJ0MmNFOUFSSW91dVNRClF0K2V3R1BRRFZ5dWp2cG9qakF2NXZlQlpRSURBUUFCbzBJd1FEQU9CZ05WSFE4QkFmOEVCQU1DQWdRd0R3WUQKVlIwVEFRSC9CQVV3QXdFQi96QWRCZ05WSFE0RUZnUVVQMkN1bkZvcmxDSzZxWU1Fc3RSTUt1NjlhYlF3RFFZSgpLb1pJaHZjTkFRRUxCUUFEZ2dHQkFEOVZvN2tGb1F1Q3puV1luUitZRlZZeDNpN2NUWHg5NWQ0eHhQQTdRR2w5CmNKbE14a250RysrZFJvZnRPeDdFNnpLVXZheFpWUXkra05nM2xSY1V0bXRsYXhHNmU1TDhSZXYxVklLYlV2RFkKSXNYbDlVbmlyM3BrRHlEZ1FiSkdTbkh2eHltNUNjMTVDK2gvWDh6aTVaelBFRjBIQ2NJRHJxZmxKN2FyRUg4eQorcDVSN1k1QnNXVmFrY2RnMnR0ZUxBTHlKNlZuNWREakRSeHFjS1NBc28yZXRVWTU0UjVDUXl3MlRBSFJ2MHE0CmZrc2R1bUZvUnhLL0N5L0ovaEw5RSsxOGQwVjJuVjNCbGJJUlpDQTEvZWthNVNpQzA3cFVnK0loK3pJa1ZGMGIKencxcE1RVitVa1BOV0RVR0dDYUh1UVNNbk80c3piS0VLK201S2FObnhuTTVnamZVYm1wNHhhZEZTbjk1OEdNVwprcVpXWlhUQjBoazZVVW54R1hsbE5SdjZXbTBib2ltaUFHVi9sL3hTMzJXVjN5ZUZnUnEva2MyZmdBZ1pqRjl1CjZwU0F2NHc2dG9ydFVFV0RIQmRhdnBoNjF3Y2JkSFc4MEFXcHJDeCtnYWVDS3NkZ3hvd0dnZ2tHWURxa000RmYKSDY5eDlPU1BVaUN4cGluV0JYZEE2UT09Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K
                                                                               server: https://34.31.19.101
                                                                             name: gke_construction-project-382718_us-central1_onlineboutique
                                                                           contexts:
                                                                           - context:
                                                                               cluster: gke_construction-project-382718_us-central1_onlineboutique
                                                                               user: gke_construction-project-382718_us-central1_onlineboutique
                                                                             name: gke_construction-project-382718_us-central1_onlineboutique
                                                                           current-context: gke_construction-project-382718_us-central1_onlineboutique
                                                                           kind: Config
                                                                           preferences: {}
                                                                           users:
                                                                           - name: gke_construction-project-382718_us-central1_onlineboutique
                                                                             user:
                                                                               exec:
                                                                                 apiVersion: client.authentication.k8s.io/v1beta1
                                                                                 command: gke-gcloud-auth-plugin.exe
                                                                                 installHint: Install gke-gcloud-auth-plugin for use with kubectl by following
                                                                                   https://cloud.google.com/blog/products/containers-kubernetes/kubectl-auth-changes-in-gke
                                                                                 provideClusterInfo: true
'"
                  }
                  }
     /* stage('Deploy to Kubernetes') {
             withEnv(['KUBECONFIG=' + kubeconfigPath]) {
                 sh "kubectl apply -f deployment-dev.yaml"
             }
         } */


}
