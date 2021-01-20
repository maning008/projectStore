package pl.walczak.storeproject.productcatalog;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> products;

    public HashMapProductStorage() {
        this.products = new ConcurrentHashMap<>();
    }

    @Override
    public List<Product> allPublished() {
        return products.values()
                .stream()
                .filter(p ->p.getDescription() != null) //bez produktów bez opisu -> to lambda
                .filter(p -> p.getPrice() != null) // bez produktów bez ceny
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product>  getById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public void save(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }
}
