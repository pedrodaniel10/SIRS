# SIRS

Network and Computer Security Project - Medical Records

## Getting Started

- By default the port 8080 accepts HTTP requests and redirects them to port 8443 as HTTPS requests (This only work with GET)

- You can change the ports in **/medical-records/src/main/resources/application.properties** changing the

properties **_server.http.port_** and **_server.port_** respectively.

- Make sure you have installed Maven 3.x.x and JDK 8+. Also check if JAVA_HOME and M2_HOME is set properly.

## DB Setup

- See **medical-records/src/main/resources/database.properties.example** and follow the instructions in the file.

### Linux:

- Copy the files ***medical-records/src/main/resources/keys.enc*** and ***medical-records/src/main/resources/.key*** to mysql config folder (usually **/etc/mysql/**, where config file is located)

- Comment lines 2, 8, 9, 10, 11, 12 of **medical-records/src/main/resources/dbconfig-linux**, and check if the file paths for the keys are correct.

- Copy ***dbconfig-linux*** file contents to mysql config file (usually **/etc/mysql/my.cnf**)

- restart mariadb service

- access mariadb and insert the following statement:
```
	INSTALL PLUGIN file_key_management SONAME 'file_key_management.so';
```

- uncomment the lines previously commented (2, 8, 9, 10, 11, 12)

- remove the ***.key*** file from the config folder and it's good to go

- everytime the mariadb service starts, the key must be in the the config folder

### MacOs (with Homebrew):

- Copy the files ***medical-records/src/main/resources/keys.enc*** and ***medical-records/src/main/resources/.key*** to mysql config folder (usually **/usr/local/var/mysql/**, where config file is located)

- Comment lines 2, 8, 9, 10, 11, 12 of **medical-records/src/main/resources/dbconfig-macos**, and check if the file paths for the keys are correct.

- Copy ***dbconfig-macos*** file contents to mysql config file (usually **/usr/local/etc/my.cnf**)

- restart mariadb service

- access mariadb and insert the following statement:
```
	INSTALL PLUGIN file_key_management SONAME 'file_key_management.so';
```

- uncomment the lines previously commented (2, 8, 9, 10, 11, 12)

- remove the ***.key*** file from the config folder and it's good to go

- everytime the mariadb service starts, the key must be in the the config folder

## How to run

Run the following command in the directory **/medical-records/**

```
mvn clean spring-boot:run
```