# student-crud-mongodb
This is a crud application for student demo. It is build with Spring Boot, MongoDB, AngularJS, Bootstrap and Angular Material.

### Requirements

JDK 1.8

Maven 3.3.9

### Application Configuration

Change the properties for MongoDB server, MongoDB port and application port, if needed, in [application.properties](src/main/resources/application.properties) file.

### Database Initialization Steps

*use student_db*

*db.createCollection("domainSequence")*

*db.domainSequence.insert({_id:"Student", sequenceValue:0})*

### Application Startup

Navigate to project directory in command prompt and initiate following commands:

*mvn spring-boot:run*

Then, browse htttp://localhost:<port> in browser.

> Have a great time ahead.

> Happy Coding!!
