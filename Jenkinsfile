pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building backend...'
                script {
                    dir('backend/pdmovies') {
                    sh 'mvn clean install -DskipTests' // Builds the project and skips the tests
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing backend...'
                script {
                    dir('backend/pdmovies') {
                    sh 'mvn test'
                    }
                }
            }
        }
    }
}
