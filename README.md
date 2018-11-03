# SIRS
Network and Computer Security Project - Medical Records

## Getting Started
- By default the port 8080 accepts HTTP requests and redirects them to port 8443 as HTTPS requests (This only work with GET)
- You can change the ports in **/medical-records/src/main/resources/application.properties** changing the 
properties **_server.http.port_** and **_server.port_** respectively.
- Make sure you have installed Maven 3.x.x and JDK 8+. Also check if JAVA_HOME and M2_HOME is set properly.

## How to run

Run the following command in the directory **/medical-records/**

```
mvn clean spring-boot:run
```

