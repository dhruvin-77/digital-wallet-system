# Digital Wallet System

## Project Overview

Digital Wallet System is a console-based Java application developed to simulate common digital wallet operations. The system allows users to create an account, log in using a 4-digit PIN, create and manage a wallet, perform money transactions, manage beneficiaries, pay bills, view transaction history, and export wallet statements.

The project was developed to apply Java programming concepts in a practical real-world business application.

## Problem Statement

Managing basic wallet activities through separate systems can be inconvenient and difficult to track. This project provides a simple console-based system where users can perform common wallet operations from one application while maintaining user information, wallet balances, and transaction records.

## Objectives

- Develop a functional Digital Wallet System using Java.
- Apply object-oriented programming concepts in a practical application.
- Implement input validation and exception handling.
- Demonstrate file-based data persistence.
- Demonstrate database operations using JDBC and SQLite.
- Demonstrate multithreading and synchronization.
- Maintain transaction records and provide statement generation.

## Main Features

- User registration
- 4-digit PIN-based login
- Name, email, phone, and PIN validation
- Wallet creation
- Balance checking
- Add money to wallet
- Withdraw money from wallet
- Send money between registered users
- Beneficiary management
- Bill payment
- Transaction history
- Wallet statement export
- Persistent user and wallet information
- SQLite database operations using JDBC
- Exception handling
- Multithreading and synchronization demonstration
- Payment processing using interfaces, inheritance, and polymorphism

## Technologies Used

- Java 26
- Java Collections Framework
- Java File I/O
- JDBC
- SQLite
- Maven
- Object-Oriented Programming
- Multithreading and synchronization

## Project Structure

<pre>
DigitalWalletSystem/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java
│           ├── BasePayment.java
│           ├── Beneficiary.java
│           ├── BillPayment.java
│           ├── DataManager.java
│           ├── Database.java
│           ├── ElectricityPayment.java
│           ├── InternetPayment.java
│           ├── MobilePayment.java
│           ├── PaymentMethod.java
│           ├── PaymentProcessor.java
│           ├── Transaction.java
│           ├── User.java
│           ├── Wallet.java
│           ├── WalletConcurrencyDemo.java
│           ├── WalletException.java
│           └── WalletService.java
├── Screenshots/
├── report/
├── .gitignore
├── pom.xml
└── README.md
</pre>

## Application Workflow

1. The user opens the application.
2. The main menu provides Register, Login, and Exit options.
3. A new user registers using a name, email, phone number, and 4-digit PIN.
4. After login, the user enters the Wallet Menu.
5. A wallet can be created and used for different financial operations.
6. Transactions are recorded in the wallet history.
7. User and wallet information is persisted locally.
8. The user can export a wallet statement as a text file.
9. The user can log out and return to the main menu.

## Functional Modules

### User Management

Handles user registration, login, phone validation, email validation, and PIN validation.

### Wallet Management

Provides wallet creation, balance checking, adding money, and withdrawing money.

### Money Transfer

Allows money to be transferred between registered users who have active wallets.

### Beneficiary Management

Allows users to add, view, and remove saved beneficiaries.

### Bill Payment

Supports simulated bill payments for:

- Electricity
- Water
- Mobile
- Internet
- Gas

### Transaction Management

Records wallet transactions such as:

- Add Money
- Withdrawal
- Transfer
- Receive
- Bill Payment

### Statement Generation

Generates a text-based wallet statement containing customer information, balance, and transaction history.

## Object-Oriented Programming Concepts

### Encapsulation

Classes such as `User`, `Wallet`, `Transaction`, and `Beneficiary` keep their fields private and provide methods for controlled access.

### Abstraction

The `PaymentMethod` interface and `BasePayment` abstract class define common payment behavior.

### Inheritance

`ElectricityPayment`, `MobilePayment`, and `InternetPayment` inherit from `BasePayment`.

### Polymorphism

Different payment classes implement the same payment interface and provide their own payment processing behavior.

## Exception Handling

The project uses a custom `WalletException` to handle errors such as:

- Invalid wallet amounts
- Insufficient balance
- Blocked wallets
- Invalid beneficiaries
- Invalid bill information
- Invalid wallet operations

Input validation is also used for menu choices, phone numbers, PINs, names, email addresses, and amounts.

## File I/O and Data Persistence

Java object serialization is used to save user and wallet information locally.

Wallet statements are exported as text files using Java file-writing classes.

The following generated local files are excluded from GitHub using `.gitignore`:

- `users.dat`
- `wallet.db`
- `statements/`
- `bin/`
- `target/`
- `.class` files

## Database

The project uses SQLite as a local database and JDBC for database connectivity.

The `Database` class demonstrates:

- Database connection
- Table creation
- Insert operations
- Select operations
- Prepared statements
- Result set processing

The database contains a `jdbc_users` table for storing basic user information.

## Multithreading

`WalletConcurrencyDemo` demonstrates concurrent wallet operations using multiple threads.

The `synchronized` keyword is used to protect shared wallet balance operations and demonstrate synchronization.

## Payment Processing

The payment processing section demonstrates interfaces, abstract classes, inheritance, and polymorphism.

Payment classes include:

- `ElectricityPayment`
- `MobilePayment`
- `InternetPayment`

`PaymentProcessor` accepts a `PaymentMethod` object and processes the payment using polymorphism.

## Non-Functional Requirements

### Usability

The application provides a simple menu-driven console interface with clear prompts and messages.

### Reliability

Wallet operations validate input and prevent invalid transactions such as withdrawals greater than the available balance.

### Security

The application uses PIN-based login and does not store database usernames or database passwords.

### Maintainability

The system is divided into multiple classes, with separate classes for users, wallets, transactions, payments, database operations, and data management.

### Resource Efficiency

The application uses local file storage and SQLite, making it lightweight for a small educational application.

### Error Handling

Invalid inputs and wallet-related errors are handled using validation methods and a custom exception.

## Installation Requirements

Before running the project, install:

- Java 26 or a compatible Java version
- Visual Studio Code
- Java Extension Pack
- Maven

## How to Run

### Using Visual Studio Code

1. Open the `DigitalWalletSystem` folder in Visual Studio Code.
2. Open `src/main/java/Main.java`.
3. Select **Run Main**.
4. The application will start from the Digital Wallet System main menu.

### Using Maven

Open a terminal in the project folder and run:

    mvn clean compile

The project can then be executed using the compiled Java classes or through the Java run configuration in Visual Studio Code.

## Testing

The application was tested using the following operations:

### Test 1: User Registration

- Enter a valid name.
- Enter a valid email.
- Enter a valid 10-digit mobile number.
- Enter a 4-digit PIN.
- Confirm that registration is successful.

### Test 2: Input Validation

Test invalid:

- Menu choices
- Names
- Email addresses
- Phone numbers
- PINs
- Amounts

The system should display an appropriate validation message.

### Test 3: Wallet Creation

- Log in.
- Select Create Wallet.
- Confirm that the wallet is created successfully.
- Attempting to create another wallet should display the appropriate message.

### Test 4: Add and Withdraw Money

- Add money to the wallet.
- Check the balance.
- Withdraw an amount within the balance.
- Attempt a withdrawal greater than the balance.

### Test 5: Money Transfer

- Create wallets for two registered users.
- Send money from one wallet to another.
- Verify the sender and receiver balances.
- Verify transfer and receive transactions.

### Test 6: Beneficiaries

- Add a beneficiary.
- View saved beneficiaries.
- Remove a beneficiary.
- Verify the corresponding messages.

### Test 7: Bill Payment

- Select a bill type.
- Enter the consumer number.
- Enter a valid bill amount.
- Verify the payment and transaction record.

### Test 8: Transaction History

- Perform wallet transactions.
- Open Transaction History.
- Verify that the recorded transactions are displayed.

### Test 9: Statement Export

- Perform at least one transaction.
- Select Export Statement.
- Verify that the statement file is generated in the `statements` folder.

### Test 10: Data Persistence

- Register a user and create a wallet.
- Add money.
- Exit the application.
- Start the application again.
- Log in using the same account.
- Verify that the wallet information is still available.

## Screenshots

Screenshots of the working application are available in the `screenshots/` folder.

The screenshots demonstrate:

- Main menu
- Registration
- Login
- Wallet menu
- Add money
- Withdraw money
- Money transfer
- Beneficiary management
- Bill payment
- Transaction history
- Statement export

## Limitations

- The application is console-based and does not provide a graphical user interface.
- SQLite is used as a local database and is intended for this educational project.
- Bill payments are simulated and do not connect to real utility providers.
- Money transfers are simulated within the application.
- The system does not connect to real banking or payment networks.
- The application does not include OTP, biometric authentication, or external payment gateway integration.

## Future Enhancements

- Develop a graphical user interface or web interface.
- Integrate a secure production database.
- Add OTP or biometric authentication.
- Add real payment gateway integration.
- Add stronger transaction security.
- Add advanced reporting and analytics.
- Support multiple currencies.
- Add administrative features and monitoring.

## Learning Outcomes

Through this project, the following Java concepts were practically applied:

- Classes and objects
- Encapsulation
- Inheritance
- Abstraction
- Interfaces
- Polymorphism
- Collections
- Exception handling
- File I/O
- JDBC
- SQLite
- Multithreading
- Synchronization

## Repository Contents

The repository contains the Java source code, Maven configuration, screenshots, report, and project documentation.

Generated runtime files and local database files are excluded using `.gitignore`.

## Author

**Dhruvin Devpura**

GitHub: https://github.com/dhruvin-77
