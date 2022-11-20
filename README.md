
## Authors

 ![Logo](https://rossum.ai/use-cases/img/illust/softwares/azuga.png) 
- [@SURYA_PRAKASH_SONI](https://github.com/11suryasoni)


###
# JAVA PROJECT
![logo](https://www.oracle.com/img/tech/cb88-java-logo-001.jpg)

### Working with different technologies like Java, Spring, SpringBoot, Docker, Kubernetes, Jenkins, MySql, MongoDB, etc.

This Project Contains Linux Commands Mimicked In Java, 
Linux Pipe, REST API & generate a CSV file, 
Java File Handling & Generate an PDF report / HTML file / XML file
, chart(s) for visualizing the data that 
is fetched from the API, Java OOPs and Packaging, Zip and unzip,
E-Mail form Java, Unit testing with Junit, JAR file.

Advance Section : Docker Project, MONGODB-JAVA-CRUD, MYSQL-JAVA-CRUD, SpringBoot-Hibernate-Mysql, SpringBoot-Jpa-Hibernate-Mysql, Jenkins, etc. 

The Project Azuga Training has codes for different applications like,
#
- LINUX COMMANDS :
---> BASIC LINUX COMMAND : The project contains programs that mimics some of the basic Linux-Commands like cat, wc, head, tail, sort, ls etc

---> PIPE COMMAND : Implementations of Linux Pipe Commands.
#
- REST-API :
---> MUSEUM API : It is included with programs for major Api's GET methods to get the data from the Api's and use them to create the .csv and .json files. 

---> CRYPTO API : It is included with programs for major Api's GET methods to get the data from the Api's and use them to create the .csv and .json files. 

#
- GENERATION OF FILES :
---> PDF,HTML,XML : The data from the Api's Call method stored in .csv and .json file are formatted to the .xml, .pdf, .html formats.

#
- CHARTS CREATION :
---> BAR GRAPH, AREA CHART, PIE CHART : The Api's data in the files are used to create the charts, i.e to visualize the properties of the Api. Bar chart, Pie Chart and Line Chart are created.

#
- OOPS AND PACKAGING :
---> REST API : OOps concepts like interface, methods, objects, classes, constructors, abstraction etc were also used in the java codes.

---> ZIP AND UNZIP :
The reports created from the Api calls and Formatters were zipped, unzipped and sent through the mail programatically using java language.

---> E-MAIL : 
Sending Generated reports via E-Mail through Java Program.
#
- UNIT TESTING WITH JUNIT :
---> Generation of different Test Cases to Validate the Methods of Api and Pipe Programs.
#

- MYSQL CRUD : 
---> MYSQL CRUD Operation from Java Program.
#

- MONGODB CRUD : 
---> MONGODB CRUD Operation from Java Program.
#

- DOCKER & KUBERNETES, JENKINS: 
---> Creating Java Program Image & Pushed Into Docker Hub, Minikube for Kubernetes & Jenkins.
#

- SPRING & SPRING-BOOT, SPRING DATA JPA, HIBERNATE : 
---> SpringBoot Application Using Spring Data Jpa, Hibernate and SpringBoot Application Without Jpa Only Hibernate.
#

###
## Installation

Install Intellij idea :-

```bash
  brew cask install intellij-idea-ce
```

Install Necessary Jar's :-

```
    commons -*- 2.11.0
    gson-2.9.1
    itextpdf-5.5.13.3
    jackson -*- 2.13.4 
    json -*- 2.7.0
    json2flat-1.0.3
    opencsv-5.7.0
    xstream-1.4.19
    slf4j-*-2.0.0
    activation-1.1.1
    FileFormatter
    javax.mail.1.6.2
    junit -*- 5.9.1
    mail -*- 1.5.0
    pdfunit-java-20
    underscore-1.8.2
    apache-log4j2 -*- 2.19.0
    javaFX -*- 19
    jfreechart -*- 1.5.3
    log4j -*- 2.19.0
    
```

Links to jar's installation, Add these to the project's IDE's workspace & Extra Jar's are attatched with projects.
### 
### Prerequisites 

The Project needs some extra Prerequisites, jar files must be Installed. 
Following are Links to the required Jar files (Try Latest version).

##### 1. [pdfunit-java-2016.05.jar](http://www.pdfunit.com/en/download/)
#####  2. [javax.mail.jar](https://jar-download.com/artifacts/com.sun.mail/javax.mail/1.6.1/source-code)
#####  3. [activation-1.1.1.jar](https://jar-download.com/artifacts/javax.activation/activation/1.1.1/source-code)
##### 4. [jfreechart-1.5.3.jar](https://search.maven.org/artifact/org.jfree/jfreechart/1.5.3/jar)
##### 5. [Underscore-1.81.jar](https://mavenlibs.com/jar/file/com.github.javadev/underscore)
##### 6. [opencsv-1.7.jar](https://jar-download.com/?search_box=opencsv-1.7)
##### 7. [jflat-core-1.3.8.jar](https://jar-download.com/?search_box=JFlat)
##### 8. [commans.io.2.11.0.jar](https://mvnrepository.com/artifact/commons-io/commons-io/2.11.0)
##### 9. [itextpdf-5.1.0.jar](https://mvnrepository.com/artifact/com.itextpdf/itextpdf/5.1.0)
##### 10. [zip4j-2.11.2.jar](https://mvnrepository.com/artifact/net.lingala.zip4j/zip4j/2.11.2)

#### .
#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 

###
## Environment Variables

To run this project, you will need to add the following environment variables to your .java file.

#### 

`COMMAND_VIA_CLI` -> (e.g., vi/vim, nano, emacs, etc

`SET_CLASSPATH`

`DEFALUT_DIRECTORY_PATH` -> "/USER/VIP/DESKTOP/FOLDER1"

#### . 
#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

###
## API Reference

## The Metropolitan Museum of Art Collection API

#### Get all items

```http
  GET https://collectionapi.metmuseum.org/public/collection/v1/objects
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET https://collectionapi.metmuseum.org/public/collection/v1/objects/[objectID]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### 
#### ----------------------------------------------------------------------------------------------
#### 

## CoinGecko API V3 (Crypto API)


#### Get all items

```http
  GET https://api.coingecko.com/api/v3/global
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |


#### 
#### ----------------------------------------------------------------------------------------------
#### 

## Sakila Structure (MySql API)


#### Get all items

```http
  GET https://dev.mysql.com/doc/employee/en/sakila-structure.html
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. NO API key |


#### .
#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
###
## Documentation

Please check all submodules are attatched correctly before go to next step.


Check out the `   API  Documentation ` for reference.


[MUSEUM API - Documentation](https://metmuseum.github.io/)

[CRYPTO API - Documentation](https://www.coingecko.com/en/api/documentation)

[SAKILA STRUCTURE API - Documentation](https://dev.mysql.com/doc/employee/en/sakila-structure.html)

#### -

Check out the `   JAVA PROJECT  Documentation ` for reference.


[JAVA PROJECT - Documentation](https://drive.google.com/drive/folders/1RCN67jBB6xIvA_OXu2jypxYDOoWSeSpY?usp=sharing)

#### .

#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
###
## Roadmap

- TASK 1 : LINUX COMMANDS 

    -   --  [ BASIC LINUX COMMANDS ](https://github.com/11suryasoni/JavaTraining/tree/master/Linux%20Commands)
    -   --  [ PIPE COMMANDS ](https://github.com/11suryasoni/JavaTraining/tree/master/Linux%20Commands/PipeCommand)

- TASK 2 : REST API

    -   --  [ MUSEUM API ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/RestApi)
    -   --  [ CRYPTO API ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/RestApi)

- TASK 3 : GENERATION OF FILES

    -   --  [ PDF ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Converter)
    -   --  [ HTML ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Converter)
    -   --  [ XML ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Converter)
- TASK 4 : CHARTS

    -   --  [ BAR GRAPH ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Charts)
    -   --  [ AREA CHART ](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Charts)
    -   --  [ PIE CHART](https://github.com/11suryasoni/JavaTraining/tree/development/RestApi/Charts)
- TASK 5 : OOPS AND PACKAGING 

    -   --  [ REST API - OOPS ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/OOPS_RestApi)
    -   --  [ ZIP - UNZIP ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/Zip_Unzip)
    -   --  [ E-MAIL ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/E-Mail)
- TASK 6 : UNIT TESTING WITH JUNIT

    -   --  [ TESTING ](https://github.com/11suryasoni/JavaTraining/tree/feature/JunitTesting)
- TASK 7 : MYSQL CRUD 

    -   --  [ MUSEUM API ](https://github.com/11suryasoni/JavaTraining/tree/development/MYSQL-JAVA-CRUD)
- TASK 8 : MONGODB CRUD 

    -   --  [ MUSEUM API ](https://github.com/11suryasoni/JavaTraining/tree/development/MONGODB-JAVA-CRUD)   
- TASK 9 : SPRINGBOOT APPLICATION USING SPRING DATA JPA - HIBERNATE, MYSQL

    -   --  [SPRINGBOOT - SPRING DATA JPA - MUSEUM API ](https://github.com/11suryasoni/JavaTraining/tree/development/SpringBoot-Jpa-Hibernate-Mysql)    
- TASK 10 : SPRINGBOOT APPLICATION USING HIBERNATE, MYSQL

    -   --  [SPRINGBOOT - HIBERNATE - MUSEUM API ](https://github.com/11suryasoni/JavaTraining/tree/development/SpringBoot-Hibernate-Mysql)    
 
 
#### .
#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
### 
## Used By

This project is used by:

- AZUGA TEAM
- SURYA SONI


#### >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
###
## Acknowledgements

JAVA PROJECT is created & is currently maintained by SURYA SONI.


 - [Source to Git Repository](https://github.com/11suryasoni/JavaTraining)
 - [Source to Readmd](https://github.com/11suryasoni/JavaTraining)
 
###
## License

Java_Project is licensed under the [AZUGA_TELEMATICS](https://www.azuga.com/) 

Using : [Java 18 ](https://www.java.com/en/)


###
## Feedback

If you have any feedback, please reach out to us at suryasonirewa@gmail.com

