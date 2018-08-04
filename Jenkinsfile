pipeline {
  agent any
  stages {
    stage('build') {
      parallel {
        stage('build-backend') {
          steps {
            sh './mvnw -pl backend clean compile'
          }
        }
        stage('build-frontend') {
          steps {
            sh './mvnw -pl frontend clean compile'
          }
        }
      }
    }
  }
}