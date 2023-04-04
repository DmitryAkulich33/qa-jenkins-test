package by.tms.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {
    private final By PRODUCTS_TITLE = By.xpath("//span[text()='Products']");
    private final By CART = By.id("shopping_cart_container");
    private final By ALL_PRODUCTS = By.xpath("//div[@class='inventory_item']");

    private final String PRODUCT_CARD_LOCATOR = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
    private final String PRODUCT_PRICE_LOCATOR = PRODUCT_CARD_LOCATOR + "//div[@class='inventory_item_price']";
    private final String ADD_TO_CART_BUTTON_LOCATOR = PRODUCT_CARD_LOCATOR + "//button[text()='Add to cart']";

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
        isPageOpened();
    }

    public boolean isPageOpened() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
        return webDriver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getAllProducts() {
        return webDriver.findElements(ALL_PRODUCTS);
    }

    public ProductsPage addProductToCart(String productName) {
        By fullLocator = By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName));
        webDriver.findElement(fullLocator).click();
        return this;
    }

    public CartPage openCartPage() {
        webDriver.findElement(CART).click();
        return new CartPage(webDriver);
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_PRICE_LOCATOR, productName));
        return webDriver.findElement(fullLocator).getText();
    }
}
