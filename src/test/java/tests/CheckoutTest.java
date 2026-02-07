package tests;
import dataProviders.CheckoutDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
public class CheckoutTest {
    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;
    @BeforeMethod
    public void openLoginPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        softAssert = new SoftAssert();
    }

    //product checkout
    @Test(dataProvider = "checkoutData", dataProviderClass = CheckoutDataProvider.class)
    public void checkoutProduct(String username, String password, String firstname, String lastname, String postalcode){
        loginPage.login(username, password);
        softAssert.assertTrue(loginPage.isInventoryPageLoaded(),"Inventory page not loaded after user login");
        int cartBadgeCount = inventoryPage.addFirstItemToCart();
        softAssert.assertEquals(cartBadgeCount,1,"Cart badge count does not match to added item count");
        checkoutPage.clickCartLink();
        softAssert.assertTrue(checkoutPage.isCartPageLoaded(),"Cart page not loaded");
        softAssert.assertTrue(checkoutPage.isCartListDisplayed(), "Cart list not loaded");
        softAssert.assertTrue(checkoutPage.isRemoveButtonDisplayed(),"Remove buttons not displayed");
        checkoutPage.clickCheckout();
        softAssert.assertTrue(checkoutPage.isCheckoutStepOnePageLoaded(),"Checkout step one page not loaded");
        checkoutPage.checkout(firstname,lastname,postalcode);
        softAssert.assertTrue(checkoutPage.isCheckoutStepTwoPageLoaded(),"Checkout step two page not loaded");
        softAssert.assertTrue(checkoutPage.isSummaryInfoDisplayed(),"Checkout summary details not displayed");
        checkoutPage.clickFinish();
        softAssert.assertTrue(checkoutPage.isCheckoutCompletionPageLoaded(),"Checkout completion page not loaded");
        softAssert.assertTrue(checkoutPage.isCheckoutSuccessMessageDisplayed(),"Checkout success message not displayed");
        softAssert.assertAll();
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}


