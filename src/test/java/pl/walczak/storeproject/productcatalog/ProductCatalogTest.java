package pl.walczak.storeproject.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    public static final String MY_PRODUCT_DESC = "my product";
    public static final String MY_PRODUCT_IMAGE = "http://my_image.pl/image.jpeg";

    @Test
    public void itAllowCreateProduct() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        String productId = productCatalog.createProduct();

        Assert.assertTrue(productCatalog.getById(productId) instanceof Product);
        Assert.assertTrue(productCatalog.isExistsById(productId));

    }

    @Test
    public void itAllowSetProductDescription(){
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        String productId = productCatalog.createProduct();

        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_IMAGE);
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertEquals(MY_PRODUCT_DESC, loadedProduct.getDescription());
        Assert.assertEquals(MY_PRODUCT_IMAGE, loadedProduct.getImage());
    }

    @Test
    public void itAllowApplyPrice(){
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        String productId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(400.20));
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertTrue(BigDecimal.valueOf(400.20).equals(loadedProduct.getPrice()));

    }
    @Test
    public void itAllowsLoadAllProducts() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String draftProductId = productCatalog.createProduct();
        String productId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(400.20));
        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_IMAGE);

        List<Product> all = productCatalog.getAvailableProducts();
        assertThat(all)
                .hasSize(1)
                .extracting(Product::getId)// strzałka to lambda, Product::getId zastepuje lambde
                .contains(productId)
                .doesNotContain(draftProductId);
    }

    @Test
    public void itDenyActionOnProductThatNotExists() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        assertThatThrownBy(()->productCatalog.applyPrice("notExists", BigDecimal.valueOf(10)))
                .hasMessage("There is no product with id: notExists");
    }

    @Test
    public void exceptionOnLoadingNotExisted() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();


        assertThatThrownBy(()->productCatalog.applyPrice("notExists", BigDecimal.valueOf(10)))
                .hasMessage("There is no product with id: notExists");
        assertThatThrownBy(()->productCatalog.getById("notExists"))
                .hasMessage("There is no product with id: notExists");
        assertThatThrownBy(()->productCatalog.updateDetails("notExists", "desc", "image"))
                .hasMessage("There is no product with id: notExists");

    }

    private ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }
}
