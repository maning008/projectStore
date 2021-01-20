package pl.walczak.storeproject.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalogFacade {
    ProductStorage productStorage;

    public ProductCatalogFacade(ProductStorage productStorage) {

        this.productStorage = productStorage;
    }

    public String createProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        productStorage.save(newProduct);

        return newProduct.getId();
    }

    public boolean isExistsById(String productId) {
        return productStorage.getById(productId).isPresent();
    }

    public Product getById(String productId) {
        Product product = getProductOrException(productId);
        return product;
    }

    public void updateDetails(String productId, String productDesc, String productImage) {
        Product loaded = getProductOrException(productId);
        loaded.setDescription(productDesc);
        loaded.setImage(productImage);
    }

    public void applyPrice(String productId, BigDecimal valueOf) {
        Product loaded = getProductOrException(productId);
        loaded.setPrice(valueOf);
    }

    public List<Product> getAvailableProducts() {
        return productStorage.allPublished();

    }

    private Product getProductOrException(String productId) {
        return productStorage.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("There is no product with id: %s", productId)));
    }
}
