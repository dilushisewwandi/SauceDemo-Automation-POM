package tests;

import dataProviders.LoginDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;


public class InventoryTest{
    WebDriver driver;
    SoftAssert softAssert;
    InventoryPage inventoryPage;
    LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        inventoryPage = new InventoryPage(driver);
        loginPage = new LoginPage(driver);
    }

    //add one item to cart
    @Test(priority = 0, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void addOneItemToCart(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");

        int cartBadgeCount = inventoryPage.addFirstItemToCart();
        softAssert.assertEquals(cartBadgeCount,1,"Cart badge count does not match to added item count");

        softAssert.assertAll();
    }

    //remove one item from cart
    @Test(priority = 0, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void removeOneItemFromCart(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");

        String selectedItemName = inventoryPage.getSelectedItemName();
        int cartBadgeCount = inventoryPage.addFirstItemToCart();

        softAssert.assertEquals(cartBadgeCount,1,"Cart badge count does not match to added item count");

        inventoryPage.clickCartLink();
        softAssert.assertTrue(inventoryPage.isCartPageLoaded(),"Cart page not loaded");

        String addedItemName = inventoryPage.getAddedItemName();
        softAssert.assertEquals(addedItemName,selectedItemName,"Added item name mismatched");

        inventoryPage.removeItem();
        softAssert.assertFalse(inventoryPage.isItemPresentInCart(),"Item still present after removal");
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(),"Cart badge still visible after removal");
        inventoryPage.clickContinueShopping();
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");
        softAssert.assertEquals(inventoryPage.getFirstItemButtonText(), "Add to cart", "Button text did not reset to 'Add to cart' after removal");
        softAssert.assertAll();
    }

    //add items to cart
    @Test(priority = 0, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void addAllItemsToCart(String username, String password) {
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded");

        int addedItemCount = inventoryPage.addAllItemsToCart();
        int cartBadgeCount = inventoryPage.getCartBadgeCount();
        softAssert.assertEquals(cartBadgeCount,addedItemCount,"Cart badge count does not match to added item count");

        softAssert.assertAll();
    }

    //logout
    @Test(priority = 1,dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void logout(String username, String password){
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(), "Inventory page not loaded");

        inventoryPage.menuButtonClick();
        inventoryPage.logout();

        softAssert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"),"User not redirected to login page");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
