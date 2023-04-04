package by.tms.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By USER_NAME = By.id("user-name");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR = By.xpath("//h3[@data-test='error']");

    public static final String PASSWORD_ERROR = "Epic sadface: Password is required";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage open() {
        webDriver.get("https://www.saucedemo.com/");
        return this;
    }

    @Step("Login as {userName} with {password}")
    public void loginAs(String userName, String password) {
        webDriver.findElement(USER_NAME).sendKeys(userName);
        webDriver.findElement(PASSWORD).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Login as a standard user")
    public ProductsPage loginAsStandardUser() {
        loginAs("standard_user", "secret_sauce");
        return new ProductsPage(webDriver);
    }

    public String getErrorText() {
        return webDriver.findElement(ERROR).getText();
    }
}
