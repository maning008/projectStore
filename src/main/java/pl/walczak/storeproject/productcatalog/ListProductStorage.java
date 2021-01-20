package pl.walczak.storeproject.productcatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListProductStorage implements ProductStorage {
    List<Product> productList;
    public ListProductStorage() {
        this.productList = new ArrayList<>();
    }



    @Override
    public List<Product> allPublished() {
        return productList
                .stream()
                .filter(p ->p.getDescription() != null) //bez produktów bez opisu -> to lambda
                .filter(p -> p.getPrice() != null) // bez produktów bez ceny
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(String productId) {
        return null;
    }

    @Override
    public void save(Product newProduct) {

    }
}
