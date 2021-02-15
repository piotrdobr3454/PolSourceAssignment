# PolSourceAssignment
Full-stack Developer Assignment - Piotr Dobrasiewicz

If you have the problem with incorrect formatting of this file - open it using Notepad.
If you have any questions related to the assignment, please let me know:

email: piotr.dobrasiewicz567@gmail.com
phone: +48 516 896 261

The program was tested both on Windows and Linux.

How to run a program:

MySQL
1. Download and install MySQL. It can be get from the website (https://dev.mysql.com/downloads/installer/).
2. Open MySQL CommandLine client.
3. Set the password to database as: password
4. If Your database had different password, go to src/main/java/com/poltest/poltest/Service/NoteController, in the function GetConnectionToSQL change the password to the one you have.
5. Also in the file src/main/resources/application.properties in spring.datasource.password change the password.
6. Type: 

CREATE DATABASE poltest;

USE poltest;

CREATE TABLE Notes
( id int NOT NULL AUTO_INCREMENT primary key,
  title varchar(50) NOT NULL,
  content text NOT NULL,
  date_initial varchar(80) NOT NULL,
  date_modified varchar(80) NOT NULL,
  version varchar(10) NOT NULL,
  visibility BOOLEAN NOT NULL,
  original int NOT NULL );

Back end

Option 1: Open through Maven (Note: Maven must be installed)

Set the directory to the root folder

Type: mvn spring-boot:run

Option 2: Open through IntelIJ or Visual Studio Code

For using Visual Studio Code: 

open main folder which contains all the data through Visual Studio Code,
run the PoltestApplication in src/main/java/com/poltest/poltest

For using IntelIJ:

open the project using pom.xml file, 
run the PoltestApplication in src/main/java/com/poltest/poltest

Agree to install any required file if the program asks for it.

The message "Started PoltestApplication in" should be print out in the terminal of the program.

Front end

Install NodeJS from the website: https://nodejs.org/en/download/

Open Node.js command prompt

Set the folder to website\my-vue-app\vue-js-client-crud\src

Type: 

npm install build 

npm run serve  

Go to http://localhost:8081/ at the website                                                                                                    
