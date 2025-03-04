package org.example.factory;

import org.example.Customer;
import org.example.Order;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CustomerRandomFactory {

    private static final Random random = new Random();

    public static Set<Customer> generateCustomers() {
        return LongStream.rangeClosed(1, 10)
                .mapToObj(CustomerRandomFactory::createCustomer)
                .collect(Collectors.toSet());
    }

    private static Customer createCustomer(Long id) {
        String name = "Customer № " + random.nextInt(1, 1000);
        long randomLevel = random.nextLong(1, 5);
        Set<Order> orders = OrderRandomFactory.generateOrders();

        return new Customer(id, name, randomLevel, orders);
    }
}
