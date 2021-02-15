# PolSourceAssignment
Full-stack Developer Assignment - Piotr Dobrasiewicz

If you have the problem with incorrect formatting of this file - open it using Notepad.
If you have any questions related to the assignment, please let me know:

email: piotr.dobrasiewicz567@gmail.com
phone: +48 516 896 261

How to run a program:

MySQL
1. Download and install MySQL. It can be get from the website (https://dev.mysql.com/downloads/installer/).
2. Open MySQL CommandLine client.
3. Set the password to database as: password
If Your database had different password, go to src/main/java/com/poltest/poltest/Service/NoteController, in the function GetConnectionToSQL change the password to the one you have.
Also in the file src/main/resources/application.properties in spring.datasource.password change the password.
4. Type: 

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
5. It is recommended to use IntelIJ or Visual Studio Code for back end
6. For using Visual Studio Code: 
6.1 open main folder which contains all the data through Visual Studio Code
6.2 run the PoltestApplication in src/main/java/com/poltest/poltest
7. For using IntelIJ:
7.1 open the project using pom.xml file 
7.2 run the PoltestApplication in src/main/java/com/poltest/poltest
8. Agree to install any required file if the program asks for it 
9. The message "Started PoltestApplication in" should be print out in the terminal of the program

Front end
10. Install NodeJS from the website: https://nodejs.org/en/download/
11. Open Node.js command prompt
12. Set the folder to website\my-vue-app\vue-js-client-crud\src
13. Type: npm run serve  
14. Go to http://localhost:8081/ at the website                                                                                                    
