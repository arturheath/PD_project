pipeline {
    agent any

    environment {
        DOCKER_HOST = "host.docker.internal:2375"
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        IMAGE_TAG = "latest"  // Consider using a more unique tag like "${env.BUILD_NUMBER}" in the future
    }

    stages {
        stage('Build Backend') {
            when {
                changeset "**/backend/**"
            }
            steps {
                echo 'Building backend...'
                script {
                    dir('backend/pdmovies') {
                        sh 'mvn clean install -DskipTests' // Builds the project and skips the tests
                    }
                }
            }
        }
        stage('Build Frontend') {
            when {
                changeset "**/frontend/**"
            }
            steps {
                echo 'Building frontend...'
                script {
                    dir('frontend/pd-movies-presentation') { 
                        sh 'npm install' 
                        sh 'npm run build'
                    }
                }
            }
        }
        stage('Test Backend') {
            when {
                changeset "**/backend/**"
            }
            steps {
                echo 'Testing backend...'
                script {
                    dir('backend/pdmovies') {
                        sh 'mvn test'
                    }
                }
            }
        }
        stage('Test Frontend') {
            when {
                changeset "**/frontend/**"
            }
            steps {
                echo 'Testing frontend...'
                script {
                    dir('frontend/pd-movies-presentation') {
                         // sh 'npm install'
                         // sh 'npm test'
                    }
                }
            }
        }
        stage('Dockerize and Push Backend') {
            when {
                changeset "**/backend/**"
            }
            steps {
                script {
                    echo 'Building Docker image for Backend'
                    dir('backend/pdmovies') {
                        sh "docker build -t arturheath/pdbackend:${IMAGE_TAG} ."
                        echo 'Pushing Backend Image'
                        docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                            sh "docker push arturheath/pdbackend:${IMAGE_TAG}"
                        }
                    }
                }
            }
        }
        stage('Dockerize and Push Frontend') {
            when {
                changeset "**/frontend/**"
            }
            steps {
                script {
                    echo 'Building Docker image for Frontend'
                    dir('frontend/pd-movies-presentation') {
                        sh "docker build -t arturheath/pdfrontend:${IMAGE_TAG} ."
                        echo 'Pushing Frontend Image'
                        docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                            sh "docker push arturheath/pdfrontend:${IMAGE_TAG}"
                        }
                    }
                }
            }
        }
        stage('Deploy with Ansible') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        // Securely passing environment variables
                        sh """
                        docker run --name pd-ansible \
                          -v /var/run/docker.sock:/var/run/docker.sock \
                          -p 9123:80 --rm \
                          -e docker_username=\$DOCKER_USERNAME \
                          -e docker_password=\$DOCKER_PASSWORD \
                          pd-ansible
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            emailext(
                subject: "BUILD ${BUILD_STATUS}: Job '${JOB_NAME}' (${BUILD_NUMBER})",
                body: """<p>Build Status: ${BUILD_STATUS}</p>
                         <p>Check console output at <a href="${BUILD_URL}console">here</a></p>""",
                to: "a2023115071@isec.pt",
                mimeType: 'text/html'
            )
        }
        failure {
            emailext(
                subject: "FAILED: Job '${JOB_NAME}' (${BUILD_NUMBER})",
                body: "Something is wrong with the build ${BUILD_NUMBER}",
                to: "a2023115071@isec.pt"
            )
        }
        success {
            emailext(
                subject: "SUCCESS: Job '${JOB_NAME}' (${BUILD_NUMBER})",
                body: "Build ${BUILD_NUMBER} completed successfully",
                to: "a2023115071@isec.pt"
            )
        }
    }
}
