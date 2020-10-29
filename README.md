# Selenium Java E2E project test
* This is a test project to work with Selenium Java using:
* Selenium 	3.141.59
* Testng		7.1.0
* Log4j		2.13.3
* Extent Reporting


## Prerequisites
This code was writen to run on a **linux os** using **Maven**.

### Installing
clean maven 
```
mvn clean
```
compile the maven project
```
mvn compile
```
Run the maven test
```
mvn test
```
### Properties
* You can set the way you run the test:
* browser = (the browser you want to run on) __[chrome/FireFox]__.
* usegrid = (to use local or selenium grid) __[0-local/1-grid]__.
* getpropfrommven = (get the properties from maven) __[mvn -Dbrowser=chrome/FireFox]__

### Reports View
After test finishes you can see the logs under logs/ folder
and to open the Extent report open "index.htm" under the reports/ folder
