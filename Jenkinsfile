pipeline {
    agent any

    parameters {
        booleanParam(name: 'RUN_FULL_PIPELINE', defaultValue: false, description: 'Check this to run the full pipeline regardless of changes')
    }

    environment {
        DOCKER_HOST = "host.docker.internal:2375"
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Build Backend') {
            when {
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/backend/**")
                }
            }
            steps {
                echo 'Building backend...'
                script {
                    dir('backend/pdmovies') {
                        sh 'mvn clean install -DskipTests'
                    }
                }
            }
        }
        stage('Build Frontend') {
            when {
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/frontend/**")
                }
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
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/backend/**")
                }
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
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/frontend/**")
                }
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
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/backend/**")
                }
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
                expression {
                    return params.RUN_FULL_PIPELINE || changeset("**/frontend/**")
                }
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
            // No condition needed here as deployment might depend on the results of previous stages
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
        failure {
            emailext(
                subject: "FAILED: Job pd_movies",
                body: "Something is wrong with the pipeline for pd_movies",
                to: "a2023115071@isec.pt"
            )
        }
        success {
            emailext(
                subject: "SUCCESS: Job pd_movies",
                body: "Pipeline for pd_movies completed successfully",
                to: "a2023115071@isec.pt"
            )
        }
        aborted {
            emailext(
                subject: "ABORTED: Job pd_movies",
                body: "Pipeline for pd_movies has been aborted",
                to: "a2023115071@isec.pt"
            )
        }
    }
}
