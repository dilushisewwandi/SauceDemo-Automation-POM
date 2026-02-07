package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
    }
    private By cartLink = By.className("shopping_cart_link");
    private By cartList = By.xpath("//div[@class='cart_list']");
    private By removeButton = By.xpath("//div[@class='item_pricebar']/button");
    private By checkoutButton = By.id("checkout");
    private By firstnameField = By.id("first-name");
    private By lastnameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.name("continue");
    private By summaryInfo = By.xpath("//div[@class='summary_info']");
    private By finishButton = By.id("finish");
    private By checkoutSuccessMessage = By.xpath("//span[@class='title']");
    public void enterFirstname(String firstname){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameField)).sendKeys(firstname);
    }
    public void enterLastname(String lastname){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameField)).sendKeys(lastname);
    }
    public void enterPostalCode(String postalcode){
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(postalcode);
    }
    public void clickCartLink(){
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
    public void clickCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameField));
    }

    public void clickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
    public void clickFinish(){
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }
    public boolean isCartPageLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartList)).isDisplayed();
    }
    public boolean isCartListDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartList)).isDisplayed();
    }
    public boolean isRemoveButtonDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton)).isDisplayed();
    }
    public boolean isCheckoutStepOnePageLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameField)).isDisplayed();
    }
    public boolean isCheckoutStepTwoPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInfo)).isDisplayed();
    }
    public boolean isSummaryInfoDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInfo)).isDisplayed();
    }
    public boolean isCheckoutCompletionPageLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutSuccessMessage)).isDisplayed();
    }
    public boolean isCheckoutSuccessMessageDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutSuccessMessage)).getText().contains("Checkout: Complete!");
    }
    public void waitForCartPageLoad(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartList));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
    }
    public void checkout(String firstname, String lastname, String postalcode){
        enterFirstname(firstname);
        enterLastname(lastname);
        enterPostalCode(postalcode);
        clickContinue();
    }
}

