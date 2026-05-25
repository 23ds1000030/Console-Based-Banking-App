# Console-Based Banking Application

A robust, console-based banking application built in Java utilizing core Object-Oriented Programming (OOP) principles, a DAO-Service-Model layer architecture, and MySQL database integration for persistent storage.

## 🚀 Features

### **User Capabilities**
- **Secure Registration & Login:** Users can create an account and log in securely.
- **Account Types:** Supports both **Savings** and **Current** accounts.
- **Dynamic Transactions:** Perform real-time deposits and withdrawals.
- **Overdraft Protection:** Current accounts include a built-in overdraft limit logic.
- **Persistent Balances:** Account balances sync automatically with the database after every successful transaction.

### **Administrative Controls**
- **Admin Dashboard:** Dedicated portal accessible via secure admin credentials.
- **User Management:** View all registered users alongside their account details or delete user accounts seamlessly.

---

## 🏗️ Architecture & Project Structure

The project strictly adheres to a clean separation of concerns:
- **`model`**: Defines core entities (`User`, `Account`, `SavingsAccount`, `CurrentAccount`) and the `Transaction` interface.
- **`dao` (Data Access Object)**: Manages direct CRUD operations with the MySQL database.
- **`service`**: Orchestrates business logic and routes console menu interactions.
- **`util`**: Handles the relational database connection setup.
- **`main`**: The execution entry point (`BankingApp.java`).

---

## 🛠️ Prerequisites

Before running the application, ensure you have the following installed:
- **Java Development Kit (JDK 8 or higher)**
- **MySQL Server**
- **MySQL Connector/J (JDBC Driver)**

---

## ⚙️ Setup and Installation

### 1. Database Configuration
1. Open your MySQL terminal or workbench and create a new database:
   ```sql
   CREATE DATABASE banking_app;