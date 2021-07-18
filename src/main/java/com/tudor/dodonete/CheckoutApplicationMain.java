package com.tudor.dodonete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CheckoutApplicationMain {
    public static void main(String[] args) {
        var productSetup = Basket.addProducts(System.in);

        var currentProducts = new HashSet<>(productSetup.keySet());

        var specialDeals = Basket.setSpecialDeal(System.in, currentProducts);

        System.out.println(specialDeals);

        var dealList = List.copyOf(specialDeals);

        var products = new ArrayList<Product>();

        dealList.forEach(specialDeal -> {
            products.add(new Product(specialDeal.getSkuName(),
                    productSetup.get(specialDeal.getSkuName()),
                    true,
                    specialDeal.getSpecialDealType()));
        });
    }
}
