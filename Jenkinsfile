pipeline {
    agent any

    environment {
        DOCKER_HOST = "tcp://localhost:2375"
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        IMAGE_TAG = "latest"  // Consider using a more unique tag like "${env.BUILD_NUMBER}" in the future
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building backend...'
                script {
                    dir('backend/pdmovies') {
                        sh 'mvn clean install -DskipTests' // Builds the project and skips the tests
                    }
                }
                echo 'Building frontend...'
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
        stage('Dockerize and Push') {
            steps {
                script {
                    echo  'Building Docker image for Backend'
                    dir('backend/pdmovies') {
                        sh "docker build -t arturheath/pdbackend:${IMAGE_TAG} ."
                    }
                    echo 'Building Docker image for Frontend'
                    dir('frontend/pd-movies-presentation') {
                        sh 'docker build -t arturheath/pdfrontend:${IMAGE_TAG} .'
                    }
                    echo 'Pushing Backend Image'
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        sh "docker push arturheath/pdbackend:${IMAGE_TAG}"
                    }
                    echo 'Pushing Frontend Image'
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        sh "docker push arturheath/pdfrontend:${IMAGE_TAG}"
                    }
                }
            }
        }
        stage('Deploy with Ansible') {
            steps {
                script {
                    // Run Ansible playbook directly
                    sh '''
                    export DOCKER_HOST=${DOCKER_HOST}
                    ansible-playbook -i inventory playbook.yml
                    '''
                }
            }
        }
    }
}
