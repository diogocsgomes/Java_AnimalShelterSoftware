package com.example.gps_g21.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingVeterinariosClassTest {

    @Test
    void loadInfoVets() {
        TestingVeterinariosClass testingVetsClass = new TestingVeterinariosClass();
        boolean result = testingVetsClass.loadInfoVets();
        assertTrue(result);
    }

}