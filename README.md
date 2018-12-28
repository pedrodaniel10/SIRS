# SIRS

Network and Computer Security Project - Medical Records

## Requirements
You must have installed the following tools:
- Maven 3.x.x 
- Java Development Kit 8 (JDK 8)
- MariaDB 10.x
- Java Keytool (you must have a command alias for Java Keytool "keytool") in order to generate keys.

Also check if JAVA_HOME and M2_HOME are set properly.

## Getting Started

- By default the port 8080 accepts HTTP requests and redirects them to port 8443 as HTTPS requests (This only work with GET)

- You can change the ports in **/medical-records/server/src/main/resources/application.properties** changing the

properties **_server.http.port_** and **_server.port_** respectively.


### Setup MariaDB

- Rename the file **/medical-records/server/src/main/resources/database.properties.example** to 
**database.properties** and fill it with your database credentials. According to your OS follow
the next instructions to setup the encryption of MariaDB.

#### Linux:

- Copy the files ***medical-records/server/src/main/resources/keys.enc*** and ***medical-records/src/main/resources/.key*** to mysql config folder (usually **/etc/mysql/**, where config file is located)

- Comment lines 2, 8, 9, 10, 11, 12 of **medical-records/server/src/main/resources/dbconfig-linux**, and check if the file paths for the keys are correct.

- Copy ***dbconfig-linux*** file contents to mysql config file (usually **/etc/mysql/my.cnf**)

- Restart mariadb service

- Access mariadb and insert the following statement:
```
	INSTALL PLUGIN file_key_management SONAME 'file_key_management.so';
```

- Uncomment the lines previously commented (2, 8, 9, 10, 11, 12)

- Remove the ***.key*** file from the config folder and it's good to go

- Everytime the mariadb service starts, the key must be in the the config folder

#### MacOs (with Homebrew):

- Copy the files ***medical-records/server/src/main/resources/keys.enc*** and ***medical-records/server/src/main/resources/.key*** to mysql config folder (usually **/usr/local/var/mysql/**, where config file is located)

- Comment lines 2, 8, 9, 10, 11, 12 of **medical-records/src/main/resources/dbconfig-macos**, and check if the file paths for the keys are correct.

- Copy ***dbconfig-macos*** file contents to mysql config file (usually **/usr/local/etc/my.cnf**)

- Restart mariadb service

- Access mariadb and insert the following statement:
```
	INSTALL PLUGIN file_key_management SONAME 'file_key_management.so';
```

- Uncomment the lines previously commented (2, 8, 9, 10, 11, 12)

- Remove the ***.key*** file from the config folder and it's good to go

- Everytime the mariadb service starts, the key must be in the the config folder

#### Windows 10:

- Copy the files ***/medical-records/server/src/main/resources/keys.enc*** and ***medical-records/server/src/main/resources/.key*** 
to mysql config folder (usually **C:\Program Files\MariaDB X.X\data\\**, where config file is located)

- Copy ***dbconfig-windows*** file contents to mysql config file (usually **C:\Program Files\MariaDB X.X\data\my.ini**)

- Restart mariadb service

- Everytime the mariadb service starts, the key must be in the the config folder

## How to run
The first time you want to run the project you need to run the following command in **/medical-records/**:

```
mvn clean install
```

If there are tests failing, something is not well configured. Check section Debugging.

### Server
Run the following command in the directory **/medical-records/server/** 
#### Server 1:

```
mvn clean spring-boot:run -Dspring-boot.run.arguments="1099"  -Dserver.port="8081"
```

#### Server 2:

```
mvn clean spring-boot:run -Dspring-boot.run.arguments="1098"  -Dserver.port="8082"
```


### Front-end
Run the following command in the directory **/medical-records/front-end/**

```
mvn clean spring-boot:run
```


## Debugging
### Handle possible SAXParseException

#### Failed to read schema document 'xml.xsd', because 'http' access is not allowed due to restriction set by the accessExternalSchema property.
Create a file named **jaxp.properties** (if it doesn't exist) under **/\<path to jdk1.8.0>/jre/lib** and then write this line in it:
```
javax.xml.accessExternalSchema = all
```
