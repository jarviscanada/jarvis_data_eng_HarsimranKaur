# Introduction
(50-100 words)
Discuss the design of each app. What does the app do? What technologies have you used? (e.g. core java, libraries, lambda, IDE, docker, etc..)
Grep : This project implements the grep command commonly used in bash. It takes the regex pattern, search directory and output file as Command Line Interface arguments.
It searches for a regex pattern in a directory recursively and stores the matching lines in the specified file. The technologies used in this project are Core Java, Docker, 
Lambda, Regex, Maven, and Java Streams.
JDBC : The purpose of this project was to use JDBC in order to implement CRUD (create, read, update, delete) operations on a database. 
Twitter : 

# Quick Start
How to use your apps? 
Grep :
Files
Docker command
JDBC :
Files
bash psql_docker.sh create|start|stop
PGPASSWORD=password psql -h localhost -U postgres -d hplussport -f script_name
1. sql scripts to create tables
Docker command
Twitter : 

#Implemenation
## Pseudocode
write `process` method pseudocode.
Grep : 

JDBC :

Twitter : 

## Performance Issue
(30-60 words)
Discuss the memory issue and how would you fix it
Grep :
JDBC :
Twitter : 

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)
Grep : 1. Grep Application was tested using different regex patterns.
2. Mulitle levels of files and directories were included in the search directory to make sure that recursive search is working.
3. The output was manually tested and verified.
JDBC : 1. The output was manually tested and verified.
2. Sample rows of data were inserted in order to test the delete feature.
Twitter : 

# Deployment
How you dockerize your app for easier distribution?
Grep :
JDBC :
Twitter : 

# Improvement
List three things you can improve in this project.
Grep : 1. Include egrep and fgrep options.
2. Include file name in the output
3. Set a default output file and make the output file name optional.
JDBC : 1. Implment create, update and delete opertions for Order table.
2.
3. 
Twitter : 1.
2.
3.
