# Project Title

JSON file validation

## Getting Started

1. Download the project to your local machine from the following [GitHub repository](https://github.com/KateGiznburg/jsonValidation)
2. Open terminal
2. Type *cd %path%*, where %path% - it's a folder with a project
3. Use *mvn test*, if you would like to run tests without creating report
4. Use *mvn site*, if you would like to run tests and create the report
5. Use *mvn clear*, if you you would like to run tests and create the report from scratch
6. In order to check the report open *surefire-report.html* using browser

## Break down into end to end tests

**Test Case: defaultProviderValidation**
1. Extract all events
2. Verify if there is a default provider ("d") for each event
3. Print its value ("P") and id ("ID") in the following format "P-ID"
4. Print the name of the teams ("Nm") in the format "Team1: {1st team} | Team2: {2nd team}

**Test Case: parametersTrTrhValidation**
1. Extract all events
2. Check overall status of the match ("Epr") for each event
3. If it is 0, then none of the "Tr1", "Tr2", "Trh1" and "Trh2" should be present

**Test Case: statusOfMatchValidation**
1. Extract all events
2. Check overall status of the match ("Epr") for each event
3. For each event verify that status of the match ("Epr") is an int from this array [0,1,2,3,4,5,6,7] 

## Built With

* [Java 8](https://java.com/ru/download/) - Programming Language
* [JDK 13](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html) - Java Development Kit
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [Maven](https://maven.apache.org/) - Dependency Management and Plugins
* [JUnit](https://junit.org/junit5/) - Test Framework
* [JSON.simple](https://code.google.com/archive/p/json-simple/) - Java toolkit for JSON

## Author

* **Kate Ginzburg** - [GitHub](https://github.com/KateGiznburg) - [LinkedIn](https://www.linkedin.com/in/kateginzburg)
