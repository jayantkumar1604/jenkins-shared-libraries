def call(String ProjectName, String ImageTag, String DockerHubUser) {

    withCredentials([usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'dockerHubUser',
        passwordVariable: 'dockerHubPass'
    )]) {

        sh '''
            echo "$dockerHubPass" | docker login -u "$dockerHubUser" --password-stdin
        '''

        sh """
            docker push ${DockerHubUser}/${ProjectName}:${ImageTag}
        """
    }
}
