package org.example;


import org.example.factory.CustomerRandomFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Set<Customer> customers = CustomerRandomFactory.generateCustomers();

        System.out.println("задание 1");
        Set<Product> task1 = doTask1(customers);
        task1.forEach(System.out::println);

        System.out.println("задание 2");
        Set<Order> task2 = doTask2(customers);
        task2.forEach(System.out::println);

        System.out.println("задание 3");
        Map<List<Product>, BigDecimal> task3 = doTask3(customers);
        task3.forEach((key, value) -> {
            System.out.println("Продукты: " + key);
            System.out.println("Общая стоимость: " + value);
        });

        System.out.println("задание 4");
        List<Product> task4 = doTask4(customers);
        task4.forEach(System.out::println);

        System.out.println("задание 5");
        List<Product> task5 = doTask5(customers);
        task5.forEach(System.out::println);

        System.out.println("задание 6");
        List<Order> task6 = doTask6(customers);
        task6.forEach(System.out::println);

        System.out.println("задание 7");
        List<Product> task7 = doTask7(customers);
        task7.forEach(System.out::println);

        System.out.println("задание 8");
        long task8 = doTask8(customers);
        System.out.println(task8);

        System.out.println("задание 9");
        OptionalDouble task9 = doTask9(customers);
        System.out.println(task9);

        System.out.println("задание 10");
        Map<String, Double> task10 = doTask10(customers);
        task10.forEach((s, aDouble) -> System.out.println(s + aDouble));

        System.out.println("задание 11");
        Map<Long, Integer> task11 = doTask11(customers);
        task11.keySet()
                .forEach(id ->
                        System.out.println("Id:" + id.toString()
                                + " Количество:" + task11.get(id).toString() + " "));


        System.out.println("задание: 12");
        Map<Customer, List<Order>> task12 = doTask12(customers);
        task12.keySet()
                .forEach(customer ->
                        System.out.println("customer:" + customer.toString()
                                + " заказы:" + task12.get(customer).toString() + " "));

        System.out.println("задание: 13");
        Map<Order, Double> task13 = doTask13(customers);
        task13.forEach(
                (order, aDouble) -> System.out.println(order.toString() + "сумма продуктов заказа: " + aDouble)
        );

        System.out.println("задание: 14");
        Map<String, List<String>> task14 = doTask14(customers);
        task14.keySet()
                .forEach(
                        s -> System.out.println("категория: " + s + "название товаров: " + task14.get(s))
                );

        System.out.println("Задание 15:");
        Map<String, Product> task15 = doTask15(customers);
        task15.keySet()
                .forEach(category -> System.out.printf(
                        "Категория %s: %s;\n", category, task15.get(category).toString()));
    }

    private static Set<Product> doTask1(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books") && p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .collect(Collectors.toSet());
    }

    private static Set<Order> doTask2(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equals("Children's products")))
                .collect(Collectors.toSet());
    }

    private static Map<List<Product>, BigDecimal> doTask3(Set<Customer> customers) {
        Map<List<Product>, BigDecimal> discountToys = new HashMap<>();
        List<Product> toys = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Toys"))
                .toList();

        BigDecimal price = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Toys"))
                .map(p -> p.getPrice().subtract(p.getPrice().multiply(BigDecimal.valueOf(0.1))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        discountToys.put(toys, price);
        return discountToys;
    }

    private static List<Product> doTask4(Set<Customer> customers) {
        return customers.stream()
                .filter(c -> c.getLevel().equals(2L))
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().isAfter(LocalDate.of(2021, 1, 31)) && o.getDateOrder().isBefore(LocalDate.of(2021, 3, 31)))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());
    }

    private static List<Product> doTask5(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .collect(Collectors.toList());
    }

    private static List<Order> doTask6(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .sorted(Comparator.comparing(Order::getDateOrder).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Product> doTask7(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().equals(LocalDate.of(2021, 3, 15)))
                .peek(o -> System.out.println(o + "\n"))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());
    }

    private static long doTask8(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().getMonth().equals(Month.FEBRUARY) && o.getDateOrder().getYear() == 2021)
                .flatMap(o -> o.getProducts().stream())
                .mapToLong(p -> p.getPrice().longValue())
                .sum();
    }

    private static OptionalDouble doTask9(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().getMonth().equals(Month.MARCH) && o.getDateOrder().getDayOfMonth() == 14 && o.getDateOrder().getYear() == 2021)
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice().doubleValue())
                .average();
    }

    private static Map<String, Double> doTask10(Set<Customer> customers) {
        Map<String, Double> statisticData = new HashMap<>();
        try {
            DoubleSummaryStatistics statistics = customers.stream()
                    .flatMap(c -> c.getOrders().stream())
                    .flatMap(o -> o.getProducts().stream())
                    .filter(p -> p.getCategory().equals("Books"))
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .summaryStatistics();

            statisticData.put("сумма: ", statistics.getSum());
            statisticData.put("среднее: ", statistics.getAverage());
            statisticData.put("максикальное: ", statistics.getMax());
            statisticData.put("минимальное: ", statistics.getMin());
            statisticData.put("кол-во: ", (double) statistics.getCount());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка при обработке данных.");
            e.printStackTrace();
        }


        return statisticData;
    }

    private static Map<Long, Integer> doTask11(Set<Customer> customers) {
        Map<Long, Integer> mapOrderCount = new HashMap<>();
        customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .forEach(o -> mapOrderCount.put(o.getId(), o.getProducts().size()));
        return mapOrderCount;
    }

    private static Map<Customer, List<Order>> doTask12(Set<Customer> customers) {
        Map<Customer, List<Order>> mapCustomers = new HashMap<>();
        customers.forEach(c -> mapCustomers.put(c, c.getOrders().stream().toList()));
        return mapCustomers;
    }

    private static Map<Order, Double> doTask13(Set<Customer> customers) {
        Map<Order, Double> mapOrder = new HashMap<>();
        customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .forEach(o -> mapOrder.put(o, o.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum()));
        return mapOrder;
    }

    private static Map<String, List<String>> doTask14(Set<Customer> customers) {
        Map<String, List<String>> listCategory = new HashMap<>();
        customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .forEach(p -> listCategory
                        .computeIfAbsent(p.getCategory(), k -> new ArrayList<>())
                        .add(p.getName())
                );
        return listCategory;
    }

    private static Map<String, Product> doTask15(Set<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toMap(Product::getCategory, Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Product::getPrice))));
    }

}
