package by.tms;

import by.tms.page.LoginPage;
import by.tms.page.ProductsPage;
import by.tms.util.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkProductsCount() {
        ProductsPage productsPage = new LoginPage(webDriver).open().loginAsStandardUser();
        assertThat(productsPage.getAllProducts())
                .as("Products page should exist on the page")
                .isNotNull()
                .as("Products page should have 6 products")
                .hasSize(6);
    }
}
