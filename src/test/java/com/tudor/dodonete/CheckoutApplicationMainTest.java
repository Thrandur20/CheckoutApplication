package com.tudor.dodonete;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.AbstractMap;
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
        assertEquals(expectedResult.size(), Basket.setSpecialDeal(in, existingProducts).size());
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
        return Set.of(new SpecialDeal("B", 2, 1, MEAL_DEAL),
                new SpecialDeal("C", 2, PACKAGE_DEAL),
                new SpecialDeal(Set.of("D", "E"), 3.25, MULTI_PRICED_DEAL));
    }

}