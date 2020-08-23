# Selenium Java E2E project test
This is a test project to work with Selenium Java using:
*Selenium 	3.141.59
*Testng		7.1.0
*Log4j		2.13.3
*Extent Reporting


### Prerequisites
This code was writen to run on a linux os. 
*Maven

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
You can set the way you run the test:
*browser = <the browser you want to run on> [chrome/FireFox].
*usegrid = <to use local or selenium grid> [0-local/1-grid].
*getpropfrommven = <get the properties from maven>. 
	*mvn -Dbrowser=chrome/FireFox]

### Reports View
after test finishes you can see the logs under logs/ folder
and to open the Extent report open "index.htm" under the reports/ folder
