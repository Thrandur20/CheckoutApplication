package com.tudor.dodonete;

import java.util.Set;

public class SpecialDeal {
    private String product;
    private Set<String> productSet;
    private int quantity;
    private double price;

    public SpecialDeal(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public SpecialDeal(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public SpecialDeal(Set<String> productSet, double price) {
        this.productSet = productSet;
        this.price = price;
    }
}
