# 🛒 SauceDemo Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Selenide](https://img.shields.io/badge/Selenide-7.0.4-green?style=flat-square)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-blue?style=flat-square)
![Allure](https://img.shields.io/badge/Allure-Report-yellow?style=flat-square)
![Architecture](https://img.shields.io/badge/Pattern-Page_Object_Model-purple?style=flat-square)

## 📋 Overview

This project is a robust **E2E Test Automation Framework** designed for the [SauceDemo](https://www.saucedemo.com/) e-commerce platform. 

Built with **Java 17** and **Selenide**, it implements the **Page Object Model (POM)** design pattern to ensure scalability and maintainability. It features advanced state management, explicit wait strategies, and integrated **Allure Reports** for comprehensive test visualization.

## 🚀 Tech Stack

* **Language:** Java 17
* **Core Framework:** [Selenide](https://selenide.org/) (Selenium WebDriver wrapper)
* **Test Runner:** TestNG
* **Reporting:** Allure Reports
* **Build Tool:** Maven
* **Browser:** Google Chrome (Incognito Mode)

## 🧪 Test Coverage (7 Scenarios)

The framework covers critical business logic and edge cases:

### 1. 🔐 Authentication & Security
* **Valid Login:** Verifies successful access for standard users.
* **Locked Out User:** Validates error handling for blocked accounts.
* **Logout Flow:** Verifies secure session termination and redirection to login.

### 2. 🛍️ E2E Purchase Flow
* **Complete Cycle:** Login -> Add to Cart -> Checkout -> Payment -> Order Completion.
* **Form Validation:** Negative testing for missing required fields (e.g., Zip Code).

### 3. 📊 Product Catalog
* **Algorithm Validation:** Verifies the "Price (low to high)" sorting functionality by parsing and comparing numerical values from the UI.

### 4. 🛒 Cart Logic
* **State Management:** Validates that the cart badge counter updates correctly when adding/removing multiple items.

## ⚙️ Key Technical Features

* **Clean State Architecture:** Implements `@AfterMethod` with `closeWebDriver()` to ensure a fresh browser instance for every test, preventing "Shared State" flakiness (e.g., leftover cookies or cart items).
* **Smart Waists:** Leverages Selenide's built-in explicit waits for stability.
* **Chrome Options:** Configured to block password save popups, notifications, and run in Incognito mode.
* **Data Parsing:** Uses Java Streams to convert UI text elements into processable data (Double/Integer) for assertions.

## 📦 Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/francisco9403/SauceDemoAutomation.git](https://github.com/francisco9403/SauceDemoAutomation.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd SauceDemoAutomation
    ```
3.  **Install dependencies:**
    ```bash
    mvn clean install -DskipTests
    ```

## ▶️ Execution & Reporting

### Run Tests & Generate Report (Recommended)
This command cleans the target folder, executes all tests, and serves the Allure Report in your default browser.

```bash
mvn clean test allure:serve
