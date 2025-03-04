package org.example.factory;

import org.example.Product;
import org.example.ProductCategory;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class ProductRandomFactory {

    private static final Random random = new Random();

    public static Set<Product> generateProducts() {
        return LongStream.rangeClosed(1, 20)
                .mapToObj(ProductRandomFactory::createProduct)
                .collect(Collectors.toSet());
    }

    private static Product createProduct(Long id) {
        ProductCategory[] categories = ProductCategory.values();
        ProductCategory category = categories[random.nextInt(categories.length)];
        String name = "Product № " + random.nextInt(1, 1000);
        BigDecimal price = BigDecimal.valueOf(random.nextInt(50, 200));

        return new Product(id, name, category.getLabel(), price);
    }
}