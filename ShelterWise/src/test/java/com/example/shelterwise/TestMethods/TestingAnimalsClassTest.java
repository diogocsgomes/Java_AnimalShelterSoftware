package com.example.shelterwise.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingAnimalsClassTest {

    @Test
    void loadInfoAnimals() {

       TestingAnimalsClass testingAnimalsClass = new TestingAnimalsClass();
       boolean result = testingAnimalsClass.loadInfoAnimals();
       assertTrue(result);
    }
    @Test
    void loadInfoAnimalsForName() {
        TestingAnimalsClass testingAnimalsClass = new TestingAnimalsClass();
        boolean result = testingAnimalsClass.loadInfoAnimalsForName();
        assertTrue(result);
    }
    @Test
    void loadInfoAnimalsForType() {
        TestingAnimalsClass testingAnimalsClass = new TestingAnimalsClass();
        boolean result = testingAnimalsClass.loadInfoAnimalsForType();
        assertTrue(result);
    }
    @Test
    void loadInfoAnimalsForNameAndType() {
        TestingAnimalsClass testingAnimalsClass = new TestingAnimalsClass();
        boolean result = testingAnimalsClass.loadInfoAnimalsForNameAndType();
        assertTrue(result);
    }
    @Test
    void loadInfoAdopters(){
        TestingAnimalsClass testingAnimalsClass = new TestingAnimalsClass();
        boolean result = testingAnimalsClass.loadInfoAdopters();
        assertTrue(result);
    }
}