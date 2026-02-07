package tests;

import dataProviders.LoginDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    @BeforeMethod
    public void openLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
    }

    //login with valid credentials
    @Test(priority = 0, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void loginWithValidCredentials(String username, String password) {
        loginPage.login(username,password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");
        softAssert.assertAll();
    }

    //login with invalid credentials
    @Test(priority = 1, dataProvider = "invalidPasswordData", dataProviderClass = LoginDataProvider.class)
    public void loginWithInvalidPassword(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username and password do not match any user in this service"),"Error message not displayed correctly");
        softAssert.assertFalse(loginPage.isInventoryPageLoaded(),"User navigated to the inventory page with invalid password login");
        softAssert.assertAll();
    }

    //login with empty fields
    @Test(priority = 2, dataProvider = "emptyFieldsData", dataProviderClass = LoginDataProvider.class)
    public void loginWithEmptyFields(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username is required"),"Error message not displayed correctly");
        softAssert.assertFalse(loginPage.isInventoryPageLoaded(),"User navigated to the inventory page even empty field login");
        softAssert.assertAll();
    }

    //locked user login
    @Test(priority = 3, dataProvider = "lockedUserData", dataProviderClass = LoginDataProvider.class)
    public void lockedUserLogin(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Sorry, this user has been locked out."), "Error message not displayed correctly");
        softAssert.assertFalse(loginPage.isInventoryPageLoaded(),"User navigated to the inventory page with locked user login");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
