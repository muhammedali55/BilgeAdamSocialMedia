pipeline {
    agent any
    tools {
        jdk 'Jdk11'
     }
    stages {
        stage("build project") {
            steps {
                    gradle {
                    tasks 'build'
                    options '--stacktrace'
                    gradleOptions '-Pproject.version=${env.BUILD_NUMBER}'
                }
                echo "Java VERSION"
                sh 'java -version'
                echo 'building project...'


            }
        }
    }
}