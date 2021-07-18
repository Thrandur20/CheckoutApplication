package com.tudor.dodonete;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import static com.tudor.dodonete.SpecialDealType.*;
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
        var expectedResult = Set.of(new SpecialDeal("A", 3, 2, MULTI_PRICED_DEAL));
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        assertEquals(expectedResult, Basket.setSpecialDeal(in));
    }

    @Test
    public void testMultiPriceButFailWithScannerException() {
        String input = "A,B,C,D";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected ScannerException");
        } catch (ScannerException e) {
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testMultiPriceButFailInputFormat() {
        String input = "A,3,C";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("C"));
        }
    }

    @Test
    public void testValidPackageDeal() {
        String input = "D,3";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        var expectedResult = Set.of(new SpecialDeal("D", 3, PACKAGE_DEAL));
        assertEquals(expectedResult, Basket.setSpecialDeal(in));
    }

    @Test
    public void testPackageDealButFailFormat() {
        String input = "D";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected Scanner Exception");
        } catch (ScannerException e) {
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testPackageDealButFailParsing() {
        String input = "D,C";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("C"));
        }
    }

    @Test
    public void testValidMealDeal() {
        String input = "A/B,3";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        var expectedResult = Set.of(new SpecialDeal(Set.of("A", "B"), 3, MEAL_DEAL));
        assertEquals(expectedResult, Basket.setSpecialDeal(in));
    }

    @Test
    public void testMealDealButFailFormat() {
        String input = "D/C,3,C";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected ScannerException");
        } catch (ScannerException e) {
            assertEquals("Incorrect format", e.getMessage());
        }
    }

    @Test
    public void testMealDealButFailParsing() {
        String input = "C/D,A";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        try {
            Basket.setSpecialDeal(in);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue(e.getMessage().contains("A"));
        }
    }
}