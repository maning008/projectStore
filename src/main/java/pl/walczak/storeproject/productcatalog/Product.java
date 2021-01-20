package pl.walczak.storeproject.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID productId;
    private String description;
    private String image;
    private BigDecimal price;
    public Product(UUID productId) {
        this.productId = productId;
    }

    public String getId() {
        return productId.toString();
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
