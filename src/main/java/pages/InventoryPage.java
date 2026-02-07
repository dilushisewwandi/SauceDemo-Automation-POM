package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait;

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By addToCartButtons = By.xpath("//div[@class='pricebar']/button");
    private By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");
    private By menuButton = By.xpath("//button[@id='react-burger-menu-btn']");
    private By menuOptions = By.xpath("//div[@class='bm-menu']/nav/a");
    private By firstItemAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By selectedItemName = By.className("inventory_item_name");
    private By addedItemName = By.className("inventory_item_name");
    private By cartLink = By.className("shopping_cart_link");
    private By cartList = By.xpath("//div[@class='cart_list']");
    private By removeButton = By.xpath("//div[@class='item_pricebar']/button");
    private By continueShoppingButton = By.id("continue-shopping");


    public void clickAddToCartButton(){
        wait.until(ExpectedConditions.elementToBeClickable(firstItemAddToCartButton)).click();
    }
    public void clickCartLink(){
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }
    public void clickRemoveButton(){
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }
    public void clickContinueShopping(){
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }
    public void menuButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public boolean isCartPageLoaded(){
        return wait.until(ExpectedConditions.urlContains("cart.html"));
    }
    public boolean isItemPresentInCart() {
        return driver.findElements(addedItemName).size() > 0;
    }
    public boolean isCartBadgeDisplayed(){
        return driver.findElements(cartBadge).size()>0;
    }
    public String getFirstItemButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstItemAddToCartButton)).getText();
    }

    public int getCartBadgeCount(){
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }
    public String getSelectedItemName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectedItemName)).getText();
    }
    public String getAddedItemName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addedItemName)).getText();
    }

//    public void waitForCartItems(){
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"),"1"));
//    }

//    if add more than one item
    public void waitForCartItems(int expectedCount){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, String.valueOf(expectedCount)));
    }
    public void waitForItemRemove(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addedItemName));
    }
    public void waitForCartBadgeRemove(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
    }

    public void removeItem(){
        clickRemoveButton();
        waitForItemRemove();
        waitForCartBadgeRemove();
    }
    public int addFirstItemToCart(){
        clickAddToCartButton();
        waitForCartItems(1);
        return getCartBadgeCount();
    }
    public int addAllItemsToCart() {
        List<WebElement> addtoCartButtons = driver.findElements(addToCartButtons);
        int addedItemCount = 0;
        for (WebElement buttons : addtoCartButtons) {
            if (buttons.getText().equalsIgnoreCase("Add to Cart")) {
                buttons.click();
                addedItemCount++;
            }
        }
        return addedItemCount;
    }

    public void logout(){
        List<WebElement> menuListValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuOptions));
        for(WebElement menuList: menuListValues){
            String option = menuList.getText();
            if (option.equals("Logout")){
                menuList.click();
                break;
            }
        }
    }

}
