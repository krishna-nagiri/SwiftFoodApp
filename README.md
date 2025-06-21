# SwiftFood - Console-Based Java Food Delivery App

## 📌 Project Overview

SwiftFood is a role-based console food delivery application developed in Java as a foundational part of full-stack learning. It includes Admin, Customer, Restaurant, and Delivery Partner functionalities, with future plans to migrate into a complete Spring Boot-based web application.

---

## 🚀 Project Goal

* Implement all food delivery features using Java (OOP, lists, encapsulation, basic data simulation)
* Complete the console version within the first 7–10 days
* Transition to full-stack web development after feature completion

---
## 🧰 Tech Stack

- Java 11+
- GSON (for JSON file-based data storage)
- Visual Studio Code (recommended)
- Git & GitHub

---

## 👥 Roles and Functionalities

### 1. Admin

* Login with hardcoded credentials
* View user statistics (customers, restaurants, delivery partners)
* View number of orders, deliveries, restaurants
* Monitor live delivery status and working hours
* Register new delivery partners and restaurants

### 2. Customer

* Register or login with email and password
* View food suggestions, menu, and previous orders
* Place new orders
* Track order status
* Earn cashback points
* Give feedback to restaurants and delivery partners

### 3. Restaurant

* Login using email/password
* Manage today's menu and pricing
* View received and current orders
* View customer ratings and feedback

### 4. Delivery Partner

* Login using email/password
* View assigned orders
* Track and update order delivery status
* View customer feedback
* Track working hours and earnings

---
## 🔐 Sample Admin Login

| Field     | Value        |
|-----------|--------------|
| Username  | `admin`      |
| Password  | `admin123@`  |

---

## 📂 File and Folder Structure

```
src/
├── com/
│   └── infy/
│       ├── App.java
│       ├── models/
│       │   ├── Customer.java
│       │   ├── Restaurant.java
│       │   ├── DeliveryPartner.java
│       │   ├── Food.java
│       │   └── Order.java
│       ├── data/
│       │   ├── CustomerData.java
│       │   ├── RestaurantData.java
│       │   ├── DeliveryPartnerData.java
│       ├── services/
│       │   ├── UserLoginService.java
│       │   ├── OrderService.java
│       └── ui/
│           ├── AdminHomePage.java
│           ├── CustomerHomePage.java
│           ├── RestaurantHomePage.java
│           ├── DeliveryPartnerHomePage.java
├── lib/
├── bin/
├── resources/
├── .vscode/
```
## 🛠️ How to Run

### 🔁 Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/SwiftFoodApp.git

2. Navigate to the project folder:
 ```
  cd SwiftFoodApp
```
3. Run the app using Git Bash or Linux:
   ```
   ./runApp.sh
   ```
4. Or run using Windows CMD:
   ```
    runApp.bat
   ```
---
⚠ Make sure gson-2.10.1.jar exists in the lib/ directory.
---
## ✅ Completed Features
* [x] Project setup and structure
* [x] Admin login
* [x] View customer, restaurant, and delivery partner counts
* [x] JSON file storage (using GSON)
* [x] Run automation script (runApp.sh)
---
## ⏳ TODO (Upcoming Features)
* [ ] Role-based homepages with stats and options
* [ ] Menu and food search system
* [ ] Order placement and tracking
* [ ] Delivery partner assignment and status updates
* [ ] Feedback and rating system
* [ ] Cashback and earnings tracking
* [ ] Admin dashboard with all reports

---

## 🛣️ Future Scope

* Convert this console app into a Spring Boot web application
* Use Thymeleaf or React for frontend
* Integrate MySQL for data storage
* Implement RESTful APIs

---

## 🙌 Credits

Developed by Murali Krishna Nagiri as part of Java full-stack learning path.

---

## 📞 Support

Let me know if you'd like this saved as a file or want help pushing the updated README to GitHub.
📧 24016000mca@gmail.com

---