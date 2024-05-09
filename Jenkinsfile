pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building backend...'
                script {
                    // Assumes Maven is installed on the Jenkins agent
                    sh 'mvn clean install -DskipTests' // Builds the project and skips the tests
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing backend...'
                script {
                    // Runs tests without building the whole project again
                    sh 'mvn test'
                }
            }
        }
    }
}
