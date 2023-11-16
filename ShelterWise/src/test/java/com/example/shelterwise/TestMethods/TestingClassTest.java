package com.example.shelterwise.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingClassTest {

    @Test
    void loadInfoAnimals() {

       TestingClass testingClass = new TestingClass();
       boolean result = testingClass.loadInfoAnimals();
       assertTrue(result);
    }
}