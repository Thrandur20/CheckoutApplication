package com.tudor.dodonete;

import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CheckoutApplicationMain {
    public static void main(String[] args) {
        var productSetup = Basket.addProducts(System.in);

        var specialDeals = Basket.setSpecialDeal(System.in);

        System.out.println(specialDeals);

        var dealList = List.copyOf(specialDeals);

        var products = new ArrayList<Product>();

        dealList.forEach(specialDeal -> {
            if (!specialDeal.getSpecialDealType().equals(SpecialDealType.MEAL_DEAL)) {
                products.add(new Product(specialDeal.getSkuName(),
                        productSetup.get(specialDeal.getSkuName()),
                        true,
                        specialDeal.getSpecialDealType(),
                        specialDeal));
            } else {
                specialDeal.getProductSet().forEach(s -> {
                    products.add(new Product(s, productSetup.get(s), true, specialDeal.getSpecialDealType(), specialDeal));
                });
            }
        });

        var existingProductSkuNames = products.stream().map(Product::getSkuName).collect(Collectors.toList());

        productSetup.keySet().forEach(s -> {
            if (!existingProductSkuNames.contains(s)) {
                products.add(new Product(s, productSetup.get(s)));
            }
        });

        System.out.println("Start entering the products in the basket, type exit to finish");
        var basketEntries = inputBasket(System.in, productSetup.keySet());

        System.out.println("Calculation starting...");
        double totalPrice = calculateTotal(basketEntries, products);
        System.out.println("TOTAL PRICE: " + totalPrice);
    }

    public static List<String> inputBasket(InputStream in, Set<String> availableProducts) {
        Scanner scanner = new Scanner(System.in);
        List<String> productList = new ArrayList<>();
        String value;
        while (scanner.hasNextLine() && !((value = scanner.next()).equals("exit"))) {
            if (!availableProducts.contains(value)) {
                throw new ProductException("Product does not exist");
            }
            productList.add(value);
        }
        return productList;
    }

    public static double calculateTotal(List<String> basketEntries, List<Product> products) {
        Map<String, Long> countedProducts = basketEntries.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        double totalValue = 0;

        for (String productSku : countedProducts.keySet()) {
            Optional<Product> prd = products.stream().filter(product -> product.getSkuName().equals(productSku)).findAny();

            if (prd.isPresent()) {
                if (prd.get().isHasDeal()) {
                    if (prd.get().getSpecialDealType().equals(SpecialDealType.PACKAGE_DEAL)) {
                        if (countedProducts.get(productSku) % prd.get().getSpecialDeal().getQuantity() == 0) {
                            int div = (int) (countedProducts.get(productSku) / prd.get().getSpecialDeal().getQuantity());
                            totalValue += div * (prd.get().getSpecialDeal().getQuantity() - 1) * prd.get().getPrice();
                        } else {
                            int div = (int) (countedProducts.get(productSku) / prd.get().getSpecialDeal().getQuantity());
                            totalValue += div * (prd.get().getSpecialDeal().getQuantity() - 1) * prd.get().getPrice() + (countedProducts.get(productSku) % prd.get().getSpecialDeal().getQuantity() * prd.get().getPrice());
                        }
                    }
                    if (prd.get().getSpecialDealType().equals(SpecialDealType.MULTI_PRICED_DEAL)) {
                        if (countedProducts.get(productSku) % prd.get().getSpecialDeal().getQuantity() == 0) {
                            int div = (int) (countedProducts.get(productSku) / prd.get().getSpecialDeal().getQuantity());
                            totalValue += div * prd.get().getSpecialDeal().getPrice();
                        } else {
                            int div = (int) (countedProducts.get(productSku) / prd.get().getSpecialDeal().getQuantity());
                            totalValue += div * prd.get().getSpecialDeal().getPrice() + (countedProducts.get(productSku) % prd.get().getSpecialDeal().getQuantity() * prd.get().getPrice());
                        }
                    }
                } else {
                    totalValue += countedProducts.get(productSku) * prd.get().getPrice();
                }
            }
        }

        Map<String, Double> map = products.stream()
                .filter(product -> product.getSpecialDealType() != null)
                .filter(product -> product.getSpecialDeal().getSpecialDealType().equals(SpecialDealType.MEAL_DEAL))
                .collect(Collectors.toMap(Product::getSkuName, Product::getPrice));


        Map<Set<String>, Double> matchingMealMap = products.stream()
                .map(Product::getSpecialDeal)
                .filter(Objects::nonNull)
                .filter(specialDeal -> specialDeal.getSpecialDealType() != null)
                .filter(specialDeal -> specialDeal.getProductSet() != null)
                .filter(specialDeal -> specialDeal.getSpecialDealType().equals(SpecialDealType.MEAL_DEAL))
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toMap(SpecialDeal::getProductSet, SpecialDeal::getPrice));

        Set<Set<String>> matchedMealDeals = products.stream()
                .filter(product -> product.getSpecialDeal() != null)
                .map(Product::getSpecialDeal)
                .filter(specialDeal -> specialDeal.getProductSet() != null)
                .map(SpecialDeal::getProductSet)
                .filter(strings -> countedProducts.keySet().containsAll(strings))
                .collect(Collectors.toSet());
        for (Set<String> matchedSet : matchedMealDeals) {
            long min = map.keySet().stream().map(countedProducts::get).min(Long::compare).get();
            totalValue += min * matchingMealMap.get(matchedSet);
            Map<String, Long> newValues = new HashMap<>();
            matchedSet.forEach(s -> {
                if (!countedProducts.get(s).equals(min)) {
                    newValues.put(s, countedProducts.get(s) - min);
                }
            });

            List<Double> additionalValues = new ArrayList<>();
            newValues.keySet().forEach(s -> additionalValues.add(map.get(s) * newValues.get(s)));

            totalValue += additionalValues.stream().reduce(0.0, Double::sum);
        }


        return totalValue;
    }
}
