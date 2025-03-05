package org.example;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Order {
    private final Long id;
    private final LocalDate dateOrder;
    private final LocalDate dateDelivery;
    private final String status;
    private final Set<Product> products;

    public Order(Long id, LocalDate dateOrder, LocalDate dateDelivery, String status, Set<Product> products) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.dateDelivery = dateDelivery;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                ", dateDelivery=" + dateDelivery +
                ", status='" + status + '\'' +
                ", products=" + products +
                '}';
    }


}
