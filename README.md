# CouponSystem_JavaProject

This application is the final project I created during my software studies.<br/>
The application is a Fullstack project, it is written in Java language on the server side and in React on the client side.

### 🤔 What is the purpose of this application?
This is a coupon management system that allows companies to generate coupons as part of advertising and marketing campaigns that they run.<br/>
The system also has registered customers, the customers can purchase coupons, coupons are limited in quantity and validity, a customer is limited to one coupon of each type.<br/>
The system records the coupons purchased by each customer.<br/>
The revenue of the system is from the purchase of coupons by the customers and by the creation of new coupons by the companies.

### 💻 Access to the system is divided into three types of clients:
1. Administrator - manages the system, managing the lists of companies and the customers.<br/>
2. Company – managing a list of coupons associated with the company.<br/>
3. Customer - who buy coupons.<br/>

# The reason I created 3 versions of the server side
Since I always had a great passion and a special connection while working on the server side, during the software studying, I decided to do the server side in 3 different ways, each of them focusing on a different technological technique.

# Now, let's dive into the 3 types of projects I created

## 1️⃣ The 1st Project: Java
Writing the server side in plain old Java.<br/>
For the database I used:<br/>
-	SQL
-	JDBC
-	ConnectionPool that manage the connections queries sent to the database.<br/>

In this project I used simple login() method for authentication & authorization.<br/><br/>

👉 The current repo represent this 1st project.<br/>
https://github.com/itsmechelly/CouponSystem_JavaProject 

## 2️⃣ The 2nd Project: Spring Framework and Session Technique for Authentication & Authorization
In the second project I rewrite the core system for more recent technology - I used Java language and Spring Framework.<br/>
For the database I used:<br/>
-	Spring Hibernate JPA.<br/>

For the authentication & authorization I used the Sessions technique.<br/><br/>

Click here to see this project on Github:<br/>
https://github.com/itsmechelly/CouponSystem_SpringProject_SessionTechnique 

## 3️⃣ The 3ed Project: Spring Framework and JWT Technique for Authentication & Authorization
In the second project I rewrite the core system for more recent technology - I used Java language and Spring Framework.<br/>
For the database I used:<br/>
-	Spring Hibernate JPA.<br/>

For the authentication & authorization I used the JTW technique.<br/><br/>

Click here to see this project on Github:<br/>
https://github.com/itsmechelly/CouponSystem_SpringProject_JwtTechnique<br/><br/>
👉 NOTE: this project is the final version and deployed to AWS cloud, click to browse the website:<br/>
LINK WILL BE ADDED SOON
<br/><br/>
👉 To login, use those details:<br/>
Admin: ➡️ e-mail: admin@admin.com password: admin<br/>
Company: ➡️ e-mail: zootAllures@company.com password: zootAllures<br/>
Customer: ➡️ e-mail: cust1@cust.com password: 1111<br/>

# And what about the Client side?
This part of the application was written using React libraries and is built as a Single Page Application (SPA).<br/>
The communication between server side and client side was done using Json and RESTful API.<br/><br/>
Click here to see this project on Github:<br/>
https://github.com/itsmechelly/CouponSystem_ReactProject<br/><br/>
👉 NOTE: this project is the final version and deployed to AWS cloud, click to browse the website:<br/>
LINK WILL BE ADDED SOON
<br/><br/>
👉 To login, use those details:<br/>
Admin: ➡️ e-mail: admin@admin.com password: admin<br/>
Company: ➡️ e-mail: zootAllures@company.com password: zootAllures<br/>
Customer: ➡️ e-mail: cust1@cust.com password: 1111<br/>

# Application Architecture – 1ST Project
## Building Java Beans classes that represent the information in the database:
Java Beans are pure information classes that represent the information managed by the application.<br/>
Later, the DAO (Data Access Objects) classes will receive these Java Beans objects, define the tables in the database and translate them into SQL queries.<br/>
Below is the diagram of the Java Beans classes:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230057713-4a48283c-67f8-4b87-8c46-4c9aac19b5c4.png)

## Building a ConnectionPool that enables the management of the Connections to the database:
A basic infrastructure service called ConnectionPool was established and will be manage the connections to the database.<br/>
The ConnectionPool is a Singleton class (of which there is one and only object), which allows to manage a fixed number of Connections to the database.<br/>
The Connections are stored in a database (set type collection) at the department level.<br/>
Below is the ConnectionPool class diagram:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230059384-839e6b61-0a73-4c16-9b63-a9996e078149.png)

## Building the DAO classes - Data Access Objects that allow performing general CRUD operations on the database:
An isolation layer named DAO and its implementation DBDAO was established above the database, which will allow convenient work from Java to the database. <br/>
Data Access Objects - DAO classes are classes that allow general CRUD operations to be performed on the tables in the database.<br/><br/>
These classes:<br/>
-	Won’t perform the logic related to the application, but only general CRUD operations.
-	Generate an SQL query and execute the queries in the data institution.
-	Use the ConnectionPool to obtain a connection to the database to perform the various operations.<br/><br/>

The DAO class is an interface that is defined as a separation layer. Each of the DAO interfaces has a DBDAO class where the implementation of each method is found.<br/>
Creating the DAO interfaces is useful for several reasons. One of them is that through this separation layer, if a different database than the existing one is needed in the future, it will be possible to replace the database without touching the rest of the system, but only by replacing the DBDAO layer.<br/>
Below is the diagram of the DAO and DBDAO:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230059954-68a35bac-78f3-4a61-9818-c73cf3dc6463.png)

## Building the business logic for the three types of clients:
The three types of clients are:<br/>
-	Administrator
-	Company
-	Customer

Each client has business logic operations needed to be performed. There are 3 dedicated departments that contain all those operations - one for each client(and later will be sent to the DAO layer).<br/>

The three classes of business logic are:<br/>
-	AdminFacade – contains the business logic of Administrator.
-	CompanyFacade – contains the business logic of Company.
-	CustomerFacade – contains the business logic of Customer.

A Facade Design Pattern is used here.<br/>
Such a design allows simple operations to be outsourced, which behind the scenes use a series of extra operations.<br/>
Because these three classes need to use the DAO components - I defined an abstract base class – ClientFacade that reference to the DAO classes(it will first login() and then direct to the relevant DAO layer according to the client type).<br/>
Below is the diagram representing the Facade architecture:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230060836-4a37e0f6-3066-4804-ab92-cd5d63f43e11.png)

## Building a class that allows clients to login and then direct to the appropriate business logic:
This class is a Singleton class named LoginManager that contains a method that allows each of the three clients to login the system.<br/>
The class uses an Enum named ClientType.<br/>
If the login is successful, each user will receive back a facade class according to the client type.<br/>
Below is the diagram for Façade classes and LoginManager operations:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230061161-dfbdc9a5-a7c7-482a-8bdb-4c1d687e47c2.png)

## Building a daily job to delete expired coupons from the system (Thread):
A basic infrastructure service named CouponExpirationDailyJob was established to clean the system from expired coupons.<br/>
The job is a thread that runs in the background regularly and checks and cleans coupons once every 24 hours.<br/>
The job is executed using a Thread that runs parallel to the system activity.<br/>
Below is the job diagram for this job activity:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230061229-cd73ce6e-cdb5-4a3d-b7b8-dd2588240351.png)

## Using Custom Exceptions:
In addition to the Java exceptions, I created system-specific exceptions:<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230061387-2925e6dc-13ae-4bb0-b277-12f1cd8d9674.png)


# ⚒️ Tech Stack
Language & Framework: Java Language
<br/>
Database: JDBC, SQL, MySQL
<br/>
Architecture & Design Patterns: Singleton Pattern, Facade Pattern
<br/>
Communication between Client side and Server side: Restful API
<br/>
Client-Side: React, JavaScript, Typescript, Bootstrap 5, HTML, CSS
<br/>


<br/>
Thanks for reading,
<br/>
Chelly 👩🏻‍💻
