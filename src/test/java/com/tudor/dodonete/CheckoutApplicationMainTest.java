package com.tudor.dodonete;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class CheckoutApplicationMainTest {

    @Test
    public void testDataInsert() throws Exception {
        Map<String, Double> enterValue = Map.of("A", 0.5);
        String input = "A,0.5";
        InputStream sysBack = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(enterValue, Basket.addProducts(in));
        System.setIn(sysBack);
    }

    @Test(expected = ScannerException.class)
    public void testDataInsertButFailWithScannerException() {
        String input = "A";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Basket.addProducts(in);
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

}