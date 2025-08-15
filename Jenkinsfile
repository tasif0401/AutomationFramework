
pipeline {
  agent any
  environment {
    LT_USERNAME = credentials('LT_USERNAME')
    LT_ACCESS_KEY = credentials('LT_ACCESS_KEY')
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps {
        sh 'mvn -B -DskipTests=false test'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}
