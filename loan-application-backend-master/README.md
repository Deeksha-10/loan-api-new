# Borrow Loan App | Online Loan Application (Backend)

- An online loan application where a baning customer can apply one or more loans. Microservice can store and retrieve loans for existing customers.
- RESTful backend server developed in Spring boot enabling users to stores data persistently in a relational database.


## Technologies used
1. Java 8
2. Maven
3. Spring Boot
4. h2 database

## Steps to setup the application
1. Install JDK 8 and apache maven 3.8.8 and setup environment variables  in your system.
2. Build the project with mvn clean install cmd
3. Import existing maven project in eclipse
4. Start springboot application as run as java application
5. Once application starts, open the h2 console with given url - http://localhost:8083/h2-console
6. Login h2 console with username and password mention in application.properties file.
7. After login  run scripts given in data.sql file to insert data in Customer and Loan table.
8. Test the application by postman using url:   http://localhost:8083/loan 


Sample Requests
1.) {"customerId":1, "fname":"David","lname":"Schmidt", "loanAmt":2000.0}

2.) {"customerId":2, "fname":"Jack","lname":"Wagner", "loanAmt":2000.0}

3.) {"customerId":"", "fname":"Ryan","lname":"Wagner", "loanAmt":2000.0}
