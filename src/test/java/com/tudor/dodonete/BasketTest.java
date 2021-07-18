package com.tudor.dodonete;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

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
        } catch (ScannerException e) {
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testDataInsertButFailParsing() {
        String input = "A,B";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.addProducts(in);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("B"));
        }
    }

    @Test
    public void testValidMultiPrice() {
        String input = "A,3,2";
        SpecialDeal specialDeal = new SpecialDeal("A", 3, 2);
        var expectedResult = List.of(specialDeal);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        assertEquals(expectedResult, Basket.setMultiPriceDeals(in));
    }

    @Test
    public void testMultiPriceButFailWithScannerException(){
        String input = "A,B,C,D";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try{
            Basket.setMultiPriceDeals(in);
            fail("Should have thrown a Scanner Exception");
        }catch (ScannerException e){
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testMultiPriceButFailParsing(){
        String input = "A,3,C";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try{
            Basket.setMultiPriceDeals(in);
            fail("Expected NumberFormatException");
        }catch (ScannerException e){
            assertEquals("C", e.getMessage());
        }
    }
}