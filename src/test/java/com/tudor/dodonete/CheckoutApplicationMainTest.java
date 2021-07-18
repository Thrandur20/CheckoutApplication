package com.tudor.dodonete;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tudor.dodonete.SpecialDealType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CheckoutApplicationMainTest {

    @Test
    public void testProductListInput() {
        String userInputString = "A,0.5" + System.getProperty("line.separator") +
                "B,0.6" + System.getProperty("line.separator") +
                "C,0.25" + System.getProperty("line.separator") +
                "D,1.5" + System.getProperty("line.separator") +
                "E,2.0" + System.getProperty("line.separator") +
                "exit";
        ByteArrayInputStream in = new ByteArrayInputStream(userInputString.getBytes());
        var expectedResult = productList();
        assertEquals(expectedResult, Basket.addProducts(in));
    }

    @Test
    public void testSpecialDeals() {
        String userInputString = "B,2,1" + System.getProperty("line.separator") +
                "C,3" + System.getProperty("line.separator") +
                "D/E,3.25" + System.getProperty("line.separator") +
                "exit";

        var existingProducts = Set.of("A", "B", "C", "D", "E");

        ByteArrayInputStream in = new ByteArrayInputStream(userInputString.getBytes());
        var expectedResult = specialDeals();
        assertEquals(expectedResult.size(), Basket.setSpecialDeal(in).size());
    }

    @Test
    public void testCalculation() {
        var inputBasket = List.of("A", "A", "A", "B", "B", "B", "B", "C", "C", "C", "C", "C", "D", "D", "D", "E", "E");
        var totalPrice = CheckoutApplicationMain.calculateTotal(inputBasket, defaultProductList());
        System.out.println(totalPrice);
    }

    private Map<String, Double> productList() {
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("A", 0.5),
                new AbstractMap.SimpleEntry<>("B", 0.6),
                new AbstractMap.SimpleEntry<>("C", 0.25),
                new AbstractMap.SimpleEntry<>("D", 1.5),
                new AbstractMap.SimpleEntry<>("E", 2.0)
        );
    }

    private Set<SpecialDeal> specialDeals() {
        return Set.of(new SpecialDeal("B", 2, 1, MULTI_PRICED_DEAL),
                new SpecialDeal("C", 3, PACKAGE_DEAL),
                new SpecialDeal(Set.of("D", "E"), 3.25, MEAL_DEAL));
    }

    private List<Product> defaultProductList() {
        return List.of(
                new Product("A", 0.5),
                new Product("B", 0.6, true, MULTI_PRICED_DEAL, new SpecialDeal("B", 2, 1, MULTI_PRICED_DEAL)),
                new Product("C", 0.25, true, PACKAGE_DEAL, new SpecialDeal("C", 3, PACKAGE_DEAL)),
                new Product("D", 1.5, true, MEAL_DEAL, new SpecialDeal(Set.of("D", "E"), 3.25, MEAL_DEAL)),
                new Product("E", 2.0, true, MEAL_DEAL, new SpecialDeal(Set.of("D", "E"), 3.25, MEAL_DEAL))
        );
    }

}