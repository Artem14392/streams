package org.example;

public enum ProductCategory {
    BOOKS("Books"),
    PRODUCT_CATEGORY("Children's products"),
    TOYS("Toys");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
