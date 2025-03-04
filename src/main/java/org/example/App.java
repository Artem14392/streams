package org.example;


import org.example.factory.CustomerRandomFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
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
        long sumPrice = doTask3(customers);
        System.out.println(sumPrice);

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

    }
    private static Set<Product> doTask1(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books") && p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .collect(Collectors.toSet());
    }

    private static Set<Order> doTask2(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equals("Children's products")))
                .collect(Collectors.toSet());
    }

    private static long doTask3(Set<Customer> customers){
        long toysPrice = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Toys"))
                .mapToLong(p -> p.getPrice().longValue())
                .sum();
        long generalPrice = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .mapToLong(p -> p.getPrice().longValue())
                .sum();
        return (long) (generalPrice - toysPrice * 0.1);
    }

    private static List<Product> doTask4(Set<Customer> customers){
        return customers.stream()
                .filter(c -> c.getLevel().equals(2L))
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().isAfter(LocalDate.of(2021, 1, 31)) && o.getDateOrder().isBefore(LocalDate.of(2021, 3, 31)))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());
    }

    private static List<Product>doTask5(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .collect(Collectors.toList());
    }

    private static List<Order> doTask6(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .sorted(Comparator.comparing(Order::getDateOrder).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Product> doTask7(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().equals(LocalDate.of(2021, 3, 15)))
                .peek(o -> System.out.println(o + "\n"))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());
    }

    private static long doTask8(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().getMonth().equals(Month.FEBRUARY))
                .flatMap(o -> o.getProducts().stream())
                .mapToLong(p -> p.getPrice().longValue())
                .sum();
    }

    private static OptionalDouble doTask9(Set<Customer> customers){
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(o -> o.getDateOrder().getMonth().equals(Month.MARCH) && o.getDateOrder().getDayOfMonth() == 14)
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice().doubleValue())
                .average();
    }

    private static Map<String, Double> doTask10(Set<Customer> customers){
        Map<String, Double> statisticData = new HashMap<>();
        double sum = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(p -> p.getPrice().doubleValue())
                .sum();

        OptionalDouble average = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(p -> p.getPrice().doubleValue())
                .average();

        OptionalDouble max = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(p -> p.getPrice().doubleValue())
                .max();

        OptionalDouble min = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(p -> p.getPrice().doubleValue())
                .min();

        double count = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getCategory().equals("Books"))
                .count();

        statisticData.put("сумма: ", sum);
        if(average.isPresent()){
            statisticData.put("статистика: ", average.getAsDouble());
        }
        else throw new NoSuchElementException("значение отсутствует");

        if(max.isPresent()){
            statisticData.put("максикальное: ", max.getAsDouble());
        }
        else throw new NoSuchElementException("значение отсутствует");

        if(min.isPresent()){
            statisticData.put("минимальное: ", min.getAsDouble());
        }
        else throw new NoSuchElementException("значение отсутствует");

        statisticData.put("кол-во: ", count);

        return statisticData;
    }

}
