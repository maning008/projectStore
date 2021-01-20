package pl.walczak.storeproject.productcatalog;

import java.util.List;
import java.util.Optional;

public class JDBCProductStorage implements ProductStorage {
    @Override
    public List<Product> allPublished() {
        return null;
    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.empty();
    }

    @Override
    public void save(Product newProduct) {

    }
}
