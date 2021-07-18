package com.tudor.dodonete;

import java.util.Objects;
import java.util.Set;

public class SpecialDeal {
    private String skuName;
    private Set<String> productSet;
    private int quantity;
    private double price;
    private SpecialDealType specialDealType;

    public SpecialDealType getSpecialDealType() {
        return specialDealType;
    }

    public void setSpecialDealType(SpecialDealType specialDealType) {
        this.specialDealType = specialDealType;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Set<String> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<String> productSet) {
        this.productSet = productSet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SpecialDeal(String product, int quantity, double price, SpecialDealType specialDealType) {
        this.skuName = product;
        this.quantity = quantity;
        this.price = price;
        this.specialDealType = specialDealType;
    }

    public SpecialDeal(String product, int quantity, SpecialDealType specialDealType) {
        this.skuName = product;
        this.quantity = quantity;
        this.specialDealType = specialDealType;
    }

    public SpecialDeal(Set<String> productSet, double price, SpecialDealType specialDealType) {
        this.productSet = productSet;
        this.price = price;
        this.specialDealType = specialDealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialDeal that = (SpecialDeal) o;
        return quantity == that.quantity && Double.compare(that.price, price) == 0 && Objects.equals(skuName, that.skuName) && Objects.equals(productSet, that.productSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuName, productSet, quantity, price);
    }

    @Override
    public String toString() {
        return "SpecialDeal{" +
                "product='" + skuName + '\'' +
                ", productSet=" + productSet +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
