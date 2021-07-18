package com.tudor.dodonete;

import java.io.InputStream;
import java.util.*;

public class Basket {
    public Basket() {
    }

    public static Map<String, Double> addProducts(InputStream in) {
        Scanner scanner = new Scanner(in);
        Map<String, Double> productMap = new HashMap<>();
        System.out.println("Enter the SKU name and price, type exit to finish");
        String value;
        while (scanner.hasNextLine() && !((value = scanner.next()).equals("exit"))) {
            if (value.split(",").length != 2) {
                throw new ScannerException("Incorrect format");
            }
            String skuValue = value.split(",")[0];
            productMap.put(skuValue, Double.parseDouble(value.split(",")[1]));
        }
        return productMap;
    }

    public Set<SpecialDeal> setSpecialDeal(InputStream in, Set<String> availableProducts) {
        Scanner scanner = new Scanner(in);
        var specialDealSet = new HashSet<SpecialDeal>();
        System.out.println("If you want to create a Multi Price Deal enter via comma delimited input, the SKU Name followed by quantity and price");
        System.out.println("If you want to create a Package Deal, enter via comma delimited input, the SKU Name followed by the quantity");
        System.out.println("If you want to create a Meal deal add multiple SKU Names separated by forward slash (/) and the meal deal price separated by comma");
        System.out.println("Enter the deal, type exit to finish");
        String value;
        while (scanner.hasNextLine() && !((value = scanner.next()).equals("exit"))) {
            var product = value.split(",")[0];
            if (product.split("/").length > 1) {
                var mealDealProducts = new HashSet<>(Arrays.asList(product.split("/")));
                if (!availableProducts.containsAll(mealDealProducts)) {
                    throw new ProductException("Product does not exist or already has a deal assign to it");
                }
                if (value.split(",").length != 2) {
                    throw new ScannerException("Incorrect format");
                }

            } else {

            }
        }
    }
}
