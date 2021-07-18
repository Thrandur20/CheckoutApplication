package com.tudor.dodonete;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        }
        return null;
    }
}
