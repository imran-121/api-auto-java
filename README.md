# API-Automation

## **OVverview:**
This API framework is developed using JAVA, Maven(build automation tool), RestAssured, Cucumber, Log4j and other supporting dependencies .  


### ** Key Features:**

Required:
1. BDD approach is followed
2. Configurations can be done from files (config.properties, cucumber.properties, extent.properties, log4j.properties, pom.xml, etc)
3. You can run the test cases by tag configuration in TestRunner.java file
4. JSON object mapping is done for request and responses. You can refer to com.api.model(data structures) and com.api.controller(core logic)
5. Maven is used a dependency management tool

Capability:
6. It generates Extent report with all the step details. Report will be generated both HTML & PDF file format.
7. Generates execution logs, with detailed request and response details.
8. Feature file has examples of reading request details from json.
9. Test execution can be triggered form command line. 
10. Easy integration to CI/CD pipeline.

## **Call Flow :**

1. Maven -> Build -> TestRunner -> StepDefinition -> Controller -> Models.request
2. Maven -> Build -> TestRunner -> StepDefinition -> Controller <- Models.Resonse
3. Maven -> Build -> TestRunner -> StepDefinition -> Controller <-> Utils											   
4. Maven -> Build -> TestRunner -> StepDefinition <-> Reporting	


## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn clean test


Once the execution completes report & log will be generated in below folder.

**Report:** 		*target/report*<br>
**Log:** 		*target/logs*

