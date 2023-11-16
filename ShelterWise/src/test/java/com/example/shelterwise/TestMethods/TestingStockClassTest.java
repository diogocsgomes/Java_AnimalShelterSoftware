package com.example.shelterwise.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestingStockClassTest {
    @Test
    void loadInfoStock(){
        TestingStockClass testingStockClass = new TestingStockClass();
        boolean result = testingStockClass.loadInfoStock();
        assertTrue(result);
    }

    @Test
    void loadInfoStockByName(){
        TestingStockClass testingStockClass = new TestingStockClass();
        boolean result = testingStockClass.loadInfoStockByName();
        assertTrue(result);
    }

    @Test
    void loadInfoStockByCategory(){
        TestingStockClass testingStockClass = new TestingStockClass();
        boolean result = testingStockClass.loadInfoStockByCategory();
        assertTrue(result);
    }

    @Test
    void loadInfoStockByNameAndCategory(){
        TestingStockClass testingStockClass = new TestingStockClass();
        boolean result = testingStockClass.loadInfoStockByNameAndCategory();
        assertTrue(result);
    }
}
