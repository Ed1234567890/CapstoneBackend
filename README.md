# CapstoneBackend
Backend for capstone project

# Pop-Quiz App
Using React, Java (Spring Boot) and MariaDB

## Description

-This is a app that allows users to take a pop-quiz. 

-Based on the learning pyramid, participatory methods have higher knowledge retention.
In a study by National training Laboratories in Betel, Maine shows that the following average rates retention of learning:
5%: Lecture
10%: Reading
30%: Demostration
75%: Practice Doing

- My motivation for this project is to make an app that will help users to prepare for exam more effectively.

- I build this project because of failing exam.

- This App provides a platform for practicing for exam using quiz

- During this project, I have put knowlege of using React, Java (Spring Boot) and MariaDB to use.


## Installation

1)Import capstonedb.sql file to MariaDB

2) Create a Java version 17 spring boot project v3.1.0 
a) Replace the src folder using src folder in backend folder
b) Update application.properties in resource folder to your properties, including changing the username and password to DB, address and port number
c) Replace pom.xml with the file included, or add 
org.springframework.boot,org.mariadb.jdbc,org.projectlombok dependencies
d) Run SpringdataJpaMariaDbApplication.java to start the program


3) Create a React project
a) Replace package.json
b) Open terminal on root project folder and npm install
c) Replace the src folder using the src folder in frontend folder
d) npm start to run the frontend

## Usage

- App will start at Welcome page

- Click Add Question at Nav bar to add question

- Click View Question at Nav bar to view list of questions
- Click on ID to update question and click submit

- Click Quiz to take Quiz
- Select categories by selecting checkbox
- Click take quiz to start quiz
- Next/Previous button at right side to navigate through questions
- Submit button at final question
- Displays Result page after submitting and marked

- Click ResultList to see list of results
- Click ID to see Quiz details
- Click retake quiz to take quiz again with the same questions
