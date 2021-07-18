package com.tudor.dodonete;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialDeal that = (SpecialDeal) o;
        return quantity == that.quantity && Double.compare(that.price, price) == 0 && Objects.equals(product, that.product) && Objects.equals(productSet, that.productSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productSet, quantity, price);
    }

    @Override
    public String toString() {
        return "SpecialDeal{" +
                "product='" + product + '\'' +
                ", productSet=" + productSet +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
