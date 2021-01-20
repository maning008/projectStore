package pl.walczak.storeproject.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {


    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade(new HashMapProductStorage());
    }

    @Bean
    public ProductStorage productionStorage() {
        return new ListProductStorage();
    }

    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade(ProductStorage productStorage) {
        ProductCatalogFacade catalogFacade = new ProductCatalogFacade(productStorage);
        String pId1 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId1, BigDecimal.TEN);
        catalogFacade.updateDetails(pId1, "My Product1", "My URL");

        String pId2 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId2, BigDecimal.TEN);
        catalogFacade.updateDetails(pId2, "My Product2", "My URL");

        String pId3 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId3, BigDecimal.TEN);
        catalogFacade.updateDetails(pId3, "My Product3", "My URL");
        return catalogFacade;
    }
}
