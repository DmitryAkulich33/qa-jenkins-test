package by.tms;

import by.tms.page.LoginPage;
import by.tms.page.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Check product price in the cart")
    @Test
    public void checkProductPriceInTheCart() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new LoginPage(webDriver)
                .open()
                .loginAsStandardUser()
                .addProductToCart(productName);
        String expectedPrice = productsPage.getProductPrice(productName);
        String actualPrice = productsPage.openCartPage().getProductPrice(productName);

        assertThat(actualPrice).as("Product should be added with correct price").isEqualTo(expectedPrice);
    }
}
