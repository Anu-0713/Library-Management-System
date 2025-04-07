# Library Management System 📚

A simple and functional Library Management System built using Java and MySQL.  
This project provides essential features like adding books, issuing books to students, returning books, and managing librarians.

## Features ✨

- Add, delete, and view books
- Issue books to students
- Return books and update inventory
- Manage librarian accounts
- User-friendly GUI built with Java Swing
- Database operations using JDBC

## Technologies Used 🛠️

- **Java** (Core Java, Swing for GUI)
- **MySQL** (Database management)
- **MySQL Connector/J** (JDBC driver to connect Java application with MySQL database)
- **IDE Recommendation**: Eclipse IDE for Enterprise Java Developers *(Recommended for better GUI and JDBC support)*

## Prerequisites ✅

Before running the application, ensure you have the following installed on your system:

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (JDBC Driver)
- Eclipse IDE for Enterprise Java Developers (or any IDE that supports Java and Swing)

## How to Run 🚀

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Anu-0713/Library-Management-System.git
   
2. **Setup the Database**
    
    -Open MySQL Workbench or any MySQL client.

    -Create a database named db.

   -Import the required tables and schema manually based on the project files:

       -> books table
     
       -> librarian table
     
       -> issuebooks table

     
3.**Configure Database Connection**

   In the Java files, update the following fields with your MySQL credentials:

       private static final String URL = "jdbc:mysql://localhost:3306/db";
   
       private static final String USER = "your_mysql_username";
   
       private static final String PASSWORD = "your_mysql_password";



4.**Add MySQL Connector JAR to Project**

   In Eclipse, right-click the project > Build Path > Add External Archives.

   Select the downloaded mysql-connector JAR file and add it.




5.**Run the Application**

   Open Library.java and run it as a Java Application.

   The GUI will launch. Start managing your library!



## Optional Notes 📝
You can enhance the UI and features further (e.g., validations, reporting, etc.).

Always ensure MySQL server is running before starting the application.

Test your database connection after updating the credentials.

Contribution 🤝
Feel free to fork the project and contribute improvements! Pull requests are welcome.
