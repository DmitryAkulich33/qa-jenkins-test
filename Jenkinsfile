pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
        credentials credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: 'f06ff511-214e-4a9e-af18-02716fdbc2eb', name: 'STANDARD_USER_CREDS', required: true
        choice choices: ['testng.xml', 'testng2.xml'], name: 'TEST_NG_XML'
    }

    stages {
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${params.STANDARD_USER_CREDS}", passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    // Get some code from a GitHub repository
                    git branch: "${params.BRANCH}", url: 'https://github.com/DmitryAkulich33/qa-jenkins-test.git'


                    // Run Maven on a Unix agent.
                    // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                    // To run Maven on a Windows agent, use
                    bat "mvn clean test -DsuiteXmlFile=src/test/resources/${params.TEST_NG_XML}"
                }
            }
        }
        stage('Reports') {
            steps {
                script {
                    allure([
                            includeProperties: false,
                            jdk              : '',
                            properties       : [],
                            reportBuildPolicy: 'ALWAYS',
                            results          : [[path: 'target/allure-results']]
                    ])
                }
            }
        }

    }
}
