package by.tms;

import by.tms.page.LoginPage;
import by.tms.page.ProductsPage;
import org.testng.annotations.Test;

import static by.tms.page.LoginPage.PASSWORD_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void checkLoginForStandardUser() {
        ProductsPage productsPage = new LoginPage(webDriver).open().loginAsStandardUser();
        assertThat(productsPage.isPageOpened())
                .isTrue()
                .as("Products page should be opened after user logged in with valid credentials");
    }

    @Test
    public void checkLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open().loginAs("standard_user", "");
        assertThat(loginPage.getErrorText())
                .isEqualTo(PASSWORD_ERROR)
                .as("The error \"" + PASSWORD_ERROR + "\" should be displayed if password not been entered");

    }
}
