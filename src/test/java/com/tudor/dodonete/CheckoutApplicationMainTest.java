package com.tudor.dodonete;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class CheckoutApplicationMainTest {



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