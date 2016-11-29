# student-crud-mongodb
This is a crud application for student demo. It is build with Spring Boot, MongoDB, AngularJS, Bootstrap and Angular Material.

### Requirements
JDK 1.8
Maven 3.3.9

### Application Startup Steps
use student_db
db.createCollection("domainSequence")
db.domainSequence.insert({_id:"Student", sequenceValue:0})
