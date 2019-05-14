# employees

## Employee Management Rest Service

This project represents the employee management system with kafka-driven event logger.

In order to use it without Docker container, following steps should be made:  
### 1. kafka and zookeeper should be installed and running and topic "employees" should be created for kafka:

zkserver

.\bin\windows\kafka-server-start.bat .\config\server.properties

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic employees

### 2. MySQL should be installed and running and database "test" should be created. 
Also, database access credentials have to be corrected in the resources/application.properties file

### 3. In order to run service, following command should be executed in the project directory (employees):

mvn spring-boot:run

Application uses swagger2, so whole interface capabilities could be checked and tested by address: 

http://localhost:8080/swagger-ui.html#/employee-controller/

Application creates events for kafka and writes all events to the "events" db table

