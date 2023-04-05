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
Click here to see this project on Github:<br/>
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
<br/>
👉 To login, use those details:<br/>
Admin: ➡️ e-mail: admin@admin.com password: admin<br/>
Company: ➡️ e-mail: zootAllures@company.com password: zootAllures<br/>
Customer: ➡️ e-mail: cust1@cust.com password: 1111<br/>

# And what about the Client side?
This part of the application was written using React libraries and is built as a Single Page Application (SPA).<br/>
The communication between server side and client side was done using Json and RESTful API.<br/><br/>
Click here to see this project on Github:<br/>
https://github.com/itsmechelly/CouponSystem_ReactProject<br/>
👉 NOTE: this project is the final version and deployed to AWS cloud, click to browse the website:<br/>
LINK WILL BE ADDED SOON
<br/>
👉 To login, use those details:<br/>
Admin: ➡️ e-mail: admin@admin.com password: admin<br/>
Company: ➡️ e-mail: zootAllures@company.com password: zootAllures<br/>
Customer: ➡️ e-mail: cust1@cust.com password: 1111<br/>

# Application Architecture – 1ST Project
##Building Java Beans classes that represent the information in the database:
Java Beans are pure information classes that represent the information managed by the application.<br/>
Later, the DAO (Data Access Objects) classes will receive these Java Beans objects, define the tables in the database and translate them into SQL queries.<br/>
Below is the diagram of the Java Beans classes:<br/><br/>

![image](https://user-images.githubusercontent.com/60425986/230057713-4a48283c-67f8-4b87-8c46-4c9aac19b5c4.png)





# Extra Details

### 👉 Communication Between the Microservice Modules
The communication between the two verticles will be by using [Vert.x Event Bus](https://vertx.io/).
### 👉 Cluster Manager
The Application will be running in cluster mode by using [Hazelcast In-Memory Data Grid (IMDG)Open Source](https://hazelcast.com/).
### 👉 Docker
The maven package will be generating a docker-compose YAML that will contain two containers for the 2 verticles.

# 🚍 Tech Stack
Language & Framework: Java, Maven
<br/>
Asynchronous Tools: Eclipse Vert.x
<br/>
In-Memory Data Grid (IMDG): Hazelcast
<br/>
Architecture & Design Patterns: Microservice Application, Reactive Application
<br/>
Client-Side UI: HTML, CSS, Bootstrap 5
<br/>

# Application Architecture

## RestVerticle module:
🛠 This java module will be a Vert.x verticle and will contain 2 classes:<br/>
### Main class:
`main(String[] args)` – To run the application in a cluster mode, the main class will use Vert.x implementation of Hazelcast as a cluster manager <br/><br/>
`getAddress()` – This method will use the NetworkInterface to locate and filter the IP addresses.<br/>
The relevant IP address will be sent back to the main method.<br/>
### RestVerticle class:
🛠 First, we will start by creating HTTP Server and Router:<br/><br/>
`start(Promise<Void> startPromise)` – This method starts an HTTP server.
The method create a Vert.x HTTP server and then handle requests using the router generated from the createRouter() method (listening to port 8080).<br/><br/>
`createRouter()` – This method creates a Vert.x Web Router (the object used to route HTTP requests to specific request handlers). The method will return Vert.x Web Router.<br/><br/>
🛠 Then, this class will expose a REST API (using Vert.x) with 5 REST methods:<br/><br/>
`GET: greetingHandler(RoutingContext context)` – This method greeting the user at the main endpoint.<br/><br/>
`POST: loginHandler(RoutingContext context)` – This method will use the RoutingContext interface to get the username and password from the user, and check if the user can be logged in (username and password will be saved in local JSON file). In the background the module should open a session for each user that logged in.<br/><br/>
`POST: logoutHandler(RoutingContext context)` – This method will be used to log out from the user session, his session will be destroyed.<br/><br/>
`POST: addOrderHandler(RoutingContext context)` – This method will add an order to the user.<br/>
By using Vert.x Event Bus, the order will be sent to the OrderVerticle module.<br/><br/>
`GET: getOrdersHandler(RoutingContext context)` – This method will return all user orders.<br/>
The request to get the data will be sent by Vert.x Event Bus to the OrderVerticle module.<br/><br/>
🛠 Extra methods used in this class to support those REST methods:<br/><br/>
`sessionAuth(RoutingContext context)` – Helper method to check if users session is permitted. <br/><br/>
`contextResponse(RoutingContext context, String errorValue, String loginValue, Integer httpStatus)` – Helper method to print error values in case one of the endpoints collapse, or get runtime error.<br/>
This method used in other methods exist in this java class, I added it for clean code. 😊

## OrderVerticle module:
🛠 This java module will be a vert.x verticle and will contain 2 classes:
### Main class:
`main(String[] args)` – To run the application in a cluster mode, the main class will use Vert.x implementation of Hazelcast as a cluster manager. This method will generate the hazelcast configuration and set the destination address.<br/><br/>
`getAddress()` – This method will use the NetworkInterface to locate and filter the IP addresses.<br/>
The relevant IP address will be sent back to the main method.
### OrderVerticle class:
🛠 First, by using Vert.x Event Bus, we will manage requests that received:<br/><br/>
`start(Promise<Void> promise)` – This method use Verte.x Event Bus to manage requests received from the RestVertical module. The Event Bus will direct each request to the relevant method.<br/><br/>
`addOrder(Message<Object> message, String orderId, String orderName, String orderDate)` – This method add new orders to the user existing orders. All the data will be saved in a local JSON file and include: orderID, orderName and orderDate. The response will be sent to the OrderVerticle module.<br/><br/>
`getOrders(Message<Object> message)` – This method will return all user orders.
The response will be sent to the OrderVerticle module.<br/><br/>
🛠 Extra method used in this class to support other methods:<br/><br/>
`messageResponse(Message<Object> message, String errorValue, String insertValue)` –  Helper method to print error values in case one of the endpoints collapse, or get runtime error.<br/>
This method used in other methods exist in this java class, I added it for clean code. 😊


# Endpoints
👉 To run this Microservice properly, you should first run RestVerticle Module, and then the OrderVerticle Module.
#### GET: greetingHandler(RoutingContext context)
```http
  	http://localhost:8080
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `RoutingContext` | **Required**. The user context |

![image](https://user-images.githubusercontent.com/60425986/229527473-11857d22-231e-4779-8919-4d91a58970a6.png)
#### POST: loginHandler(RoutingContext context)
```http
  	http://localhost:8080/login
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `RoutingContext` | **Required**. The user context |

![image](https://user-images.githubusercontent.com/60425986/229525638-a08446d6-fca6-4ad4-a433-b209cd5420b5.png)
#### POST: logoutHandler(RoutingContext context)
```http
  	http://localhost:8080/logout
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `RoutingContext` | **Required**. The user context |

![image](https://user-images.githubusercontent.com/60425986/229525701-df045808-6012-426b-929f-8c981372d0d6.png)

#### POST: addOrderHandler(RoutingContext context)
```http
  	http://localhost:8080/add-order
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `RoutingContext` | **Required**. The user context |

![image](https://user-images.githubusercontent.com/60425986/229525766-db8206d2-34cb-46b1-bc70-5f6d34896af9.png)
#### GET: getOrdersHandler(RoutingContext context)
```http
  	http://localhost:8080/get-orders
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `RoutingContext` | **Required**. The user context |

![image](https://user-images.githubusercontent.com/60425986/229525797-a11eb97a-6b43-402b-a921-ec1fc27fde02.png)

<br/>
Thanks for reading,
<br/>
Chelly 👩🏻‍💻
