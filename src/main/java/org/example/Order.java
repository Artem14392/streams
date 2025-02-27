package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Order {
    private final Long id;
    private final LocalDate dateOrder;
    private final LocalDate dateDelivery;
    private final String statuc;
    private final Set<Product> products;

    public Order(Long id, LocalDate dateOrder, LocalDate dateDelivery, String statuc, Set<Product> products) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.dateDelivery = dateDelivery;
        this.statuc = statuc;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public String getStatuc() {
        return statuc;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
