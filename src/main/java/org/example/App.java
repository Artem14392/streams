package org.example;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Customer> listCustomer = new ArrayList<>();

        Product product = new Product(234L, "milk", "eat", BigDecimal.valueOf(100));
        Product product1 = new Product(900L, "laptop", "tech", BigDecimal.valueOf(40000));
        Product product2 = new Product(9902L, "soap", "household", BigDecimal.valueOf(56));
        Product product3 = new Product(11L, "tea", "eat", BigDecimal.valueOf(90));
        Product product4 = new Product(234L, "milk", "eat", BigDecimal.valueOf(100));
        Product product5 = new Product(2322L, "phone", "tech", BigDecimal.valueOf(30000));



    }


}
