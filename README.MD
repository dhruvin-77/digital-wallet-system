# Digital Wallet System

A console-based Digital Wallet System developed in Java.

The project allows users to register, log in using a 4-digit PIN, create a wallet, manage money, send money to other users, manage beneficiaries, pay bills, view transaction history, and export wallet statements.

## Features

- User registration and login
- 4-digit PIN authentication
- 10-digit mobile number validation
- Wallet creation
- Balance checking
- Add money
- Withdraw money
- Send money between wallets
- Beneficiary management
- Bill payments
- Transaction history
- Statement export
- Persistent user data using file I/O
- SQLite database operations using JDBC
- Exception handling
- Synchronized wallet operations using multithreading
- Payment processing using interfaces, inheritance, and polymorphism

## Technologies Used

- Java
- JDBC
- SQLite
- Maven
- File I/O
- Java Collections
- Multithreading
- Object-Oriented Programming

## Project Structure

DigitalWalletSystem/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java
│           ├── User.java
│           ├── Wallet.java
│           ├── WalletService.java
│           ├── Transaction.java
│           ├── Beneficiary.java
│           ├── BillPayment.java
│           ├── WalletException.java
│           ├── DataManager.java
│           ├── Database.java
│           ├── PaymentMethod.java
│           ├── BasePayment.java
│           ├── ElectricityPayment.java
│           ├── MobilePayment.java
│           ├── InternetPayment.java
│           ├── PaymentProcessor.java
│           └── WalletConcurrencyDemo.java
├── .gitignore
├── pom.xml
└── README.md

## Main Menu

1. Register
2. Login
3. Exit

## Wallet Menu

1. Create Wallet
2. Check Balance
3. Add Money
4. Withdraw Money
5. Send Money
6. Beneficiaries
7. Pay Bill
8. Transaction History
9. Export Statement
10. Logout

## OOP Concepts Demonstrated

### Encapsulation

Classes such as `User`, `Wallet`, `Transaction`, and `Beneficiary` keep their data private and provide methods for controlled access.

### Inheritance

Payment classes inherit from the abstract `BasePayment` class.

### Polymorphism

Different payment classes implement the `PaymentMethod` interface and provide their own payment processing behavior.

### Abstraction

The `PaymentMethod` interface and `BasePayment` abstract class demonstrate abstraction.

## Exception Handling

A custom `WalletException` is used for wallet-related errors such as invalid amounts, insufficient balance, blocked wallets, invalid beneficiaries, and missing bill information.

## File I/O

User information is saved locally using Java object serialization.

Wallet statements can also be exported as text files.

Generated local files such as `users.dat`, `wallet.db`, and the `statements/` folder are excluded from GitHub using `.gitignore`.

## Database

The project uses SQLite with JDBC.

The database stores basic user information in the `jdbc_users` table.

JDBC operations demonstrated include database connection, table creation, insert operation, select operation, prepared statements, and result set processing.

## Multithreading

`WalletConcurrencyDemo` demonstrates synchronized wallet operations using multiple threads.

The `synchronized` keyword is used to protect shared wallet balance updates.

## Running the Project

Open the project in Visual Studio Code with the Java Extension Pack installed.

Open `Main.java` and select Run > Run Main.

The application starts from the main menu.

## Requirements

- Java 26 or compatible Java version
- Visual Studio Code
- Java Extension Pack
- Maven

The SQLite JDBC dependency is managed through `pom.xml`.

## Security

No database password or database credentials are stored in the project.

The SQLite database runs locally without requiring a username or password.

## Learning Purpose

This project was developed as a Java programming course project to demonstrate practical use of Java fundamentals, object-oriented programming, exception handling, collections, file I/O, JDBC, multithreading, interfaces, and inheritance.

## Author

**Dhruvin Devpura**
