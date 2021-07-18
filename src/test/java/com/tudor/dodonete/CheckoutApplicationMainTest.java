package com.tudor.dodonete;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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

    @Test
    public void testDataInsertButFailWithScannerException() {
        String input = "A";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.addProducts(in);
        }catch (ScannerException e){
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testDataInsertButFailParsing(){
        String input = "A,B";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try{
            Basket.addProducts(in);
            fail("Expected NumberFormatException");
        }catch (NumberFormatException e){

        }
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