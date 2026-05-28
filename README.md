# 🚀 SauceDemo Test Automation Framework

> A scalable and maintainable Selenium automation framework built using Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern.

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-Automation-green?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-Testing-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

---

# 📌 Project Overview

This project is a **UI Test Automation Framework** developed for the **SauceDemo Web Application** using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- IntelliJ IDEA

The framework is designed using the **Page Object Model (POM)** architecture to improve:

✔ Maintainability  
✔ Reusability  
✔ Scalability  
✔ Readability of automation code

The project demonstrates practical industry-level QA automation concepts including:

- Automated functional testing
- Data-driven testing
- Reusable page classes
- Explicit waits
- Structured test execution

---

# 🎯 Key Features

✅ Page Object Model (POM) Architecture  
✅ Selenium WebDriver Automation  
✅ TestNG Test Management  
✅ Maven Dependency Management  
✅ Data-Driven Testing using DataProviders  
✅ Explicit Wait Handling using WebDriverWait  
✅ Modular & Reusable Code Structure  
✅ Easy Test Execution & Maintenance  

---

# 🧪 Test Coverage

| Module | Test Scenarios |
|---|---|
| Login | Valid login, invalid login, empty fields, locked user validation |
| Cart Management | Add products to cart, remove products from cart |
| Checkout | Complete checkout workflow validation |
| Logout | Successful logout verification |
| UI Validation | Error message validation & page verification |

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/dilushisewwandi/SauceDemo-Automation-POM.git
```

---

## 2️⃣ Open in IntelliJ IDEA

- Open IntelliJ IDEA
- Select **Open Project**
- Import as Maven Project

---

## 3️⃣ Install Dependencies

Maven automatically downloads required dependencies.

```bash
mvn clean install
```

---

# ▶️ Running Tests

## Run All Tests

```bash
mvn test
```

---

## Run Specific Test Class

```bash
mvn -Dtest=LoginTest test
```

---

## Run Through IntelliJ IDEA

- Right-click test class
- Click **Run**

---

# 📋 Sample Test Scenario

```java
    //add one item to cart
    @Test(priority = 0, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void addOneItemToCart(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");

        int cartBadgeCount = inventoryPage.addFirstItemToCart();
        softAssert.assertEquals(cartBadgeCount,1,"Cart badge count does not match to added item count");

        softAssert.assertAll();
    }
```

---

### Future Enhancements

- Extent Reports
- Allure Reports
- Jenkins CI/CD Integration
- Cross-browser Testing
- Screenshot Capture on Failure
- Docker Support

---

# 🧠 Automation Concepts Used

| Concept | Usage |
|---|---|
| Page Object Model | Maintainable page classes |
| Explicit Waits | Stable synchronization |
| DataProviders | Data-driven testing |
| Assertions | Validation handling |
| Maven | Dependency management |
| TestNG | Test execution & grouping |

---

# 🌟 Why This Project Matters

This project demonstrates:

- Real-world automation framework design
- QA engineering best practices
- Clean coding standards
- Scalable automation architecture
- Practical Selenium & TestNG knowledge

---

# 🚀 Future Improvements

Planned upgrades for the framework:

- CI/CD pipeline integration
- GitHub Actions automation
- Extent Reports integration
- Parallel test execution
- Cross-browser support
- API testing integration
- Hybrid framework implementation

---

# 👩‍💻 Author

## Dilushika Sewwandi

---

# 📄 License

This project is created for educational and portfolio purposes.
