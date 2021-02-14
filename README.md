# PolSourceAssignment
Full-stack Developer Assignment - Piotr Dobrasiewicz

If you have any questions related to the assignment, please let me know:

email: piotr.dobrasiewicz567@gmail.com
phone: +48 516 896 261

How to run a program:

MySQL
1. Download and install MySQL. It can be get from the website (https://dev.mysql.com/downloads/installer/).
2. Open MySQL CommandLine client.
3. Set the password to database as: password
	3.1 If Your database had different password, go to src/main/java/com/poltest/poltest/Service/NoteController, 
	    in the function GetConnectionToSQL change the password to the one you have.
	3.2 Also in the file src/main/resources/application.properties in spring.datasource.password change the password.
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
4. It is recommended to use IntelIJ or Visual Studio Code for back end
5. For using Visual Studio Code: 
	5.1 open main folder which contains all the data through Visual Studio Code
	5.2 run the PoltestApplication in src/main/java/com/poltest/poltest
6. For using IntelIJ:
	6.1 open the project using pom.xml file 
	6.2 run the PoltestApplication in src/main/java/com/poltest/poltest
7.Agree to install any required file if the program asks for it 
8.  The message "Started PoltestApplication in" should be print out in the terminal of the program

Front end
9. Install NodeJS from the website: https://nodejs.org/en/download/
10. Open Node.js command prompt
11. Set the folder to website\my-vue-app\vue-js-client-crud\src
12. Type: npm run serve  
13. Go to http://localhost:8081/ at the website                                                                                                    
