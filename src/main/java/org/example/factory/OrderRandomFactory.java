package org.example.factory;

import org.example.Order;
import org.example.Product;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class OrderRandomFactory {

    private static final Random random = new Random();

    public static Set<Order> generateOrders() {
        return LongStream.rangeClosed(1, 10)
                .mapToObj(OrderRandomFactory::createOrder)
                .collect(Collectors.toSet());
    }

    private static Order createOrder(Long id) {
        LocalDate orderDate = LocalDate.of(2021, random.nextInt(2, 5), random.nextInt(1, 29));
        LocalDate deliveryDate = orderDate.plusDays(random.nextInt(1, 15));
        Set<Product> products = ProductRandomFactory.generateProducts();

        return new Order(
                id,
                orderDate,
                deliveryDate,
                "Status № " + random.nextInt(1, 1000),
                products
        );
    }
}

