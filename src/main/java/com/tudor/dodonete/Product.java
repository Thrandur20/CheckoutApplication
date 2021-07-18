package com.tudor.dodonete;

import java.util.Objects;

public class Product {
    private String skuName;
    private double price;
    private boolean hasDeal;
    private SpecialDealType specialDealType;

    public Product(String skuName, double price, boolean hasDeal, SpecialDealType specialDealType) {
        this.skuName = skuName;
        this.price = price;
        this.hasDeal = hasDeal;
        this.specialDealType = specialDealType;
    }

    public Product(String skuName, double price) {
        this.skuName = skuName;
        this.price = price;
        this.hasDeal = false;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasDeal() {
        return hasDeal;
    }

    public void setHasDeal(boolean hasDeal) {
        this.hasDeal = hasDeal;
    }

    public SpecialDealType getSpecialDealType() {
        return specialDealType;
    }

    public void setSpecialDealType(SpecialDealType specialDealType) {
        this.specialDealType = specialDealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && hasDeal == product.hasDeal && Objects.equals(skuName, product.skuName) && specialDealType == product.specialDealType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuName, price, hasDeal, specialDealType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "skuName='" + skuName + '\'' +
                ", price=" + price +
                ", hasDeal=" + hasDeal +
                ", specialDealType=" + specialDealType +
                '}';
    }
}
