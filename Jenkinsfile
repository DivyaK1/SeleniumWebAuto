pipeline 
{
    agent any
    
    tools{
    	maven 'm3'
        }

    stages 
    {
        stage('Build') 
        {  steps{
                echo("Build project")
            }
        }
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/DivyaK1/SeleniumWebAuto.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_regression.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}
