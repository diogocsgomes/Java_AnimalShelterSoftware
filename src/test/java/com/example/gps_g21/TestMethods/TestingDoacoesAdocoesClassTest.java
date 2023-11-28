package com.example.gps_g21.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestingDoacoesAdocoesClassTest {

    @Test
    void loadInfoAnimals() {
       TestingDoacoesAdocoesClass testingDoacoesAdocoesClass = new TestingDoacoesAdocoesClass();
       boolean result = testingDoacoesAdocoesClass.loadInfoDoacoesAdocoes();
       assertTrue(result);
    }
    @Test
    void loadInfoAnimalsForName() {
        TestingDoacoesAdocoesClass testingDoacoesAdocoesClass = new TestingDoacoesAdocoesClass();
        boolean result = testingDoacoesAdocoesClass.loadInfoAdocoes();
        assertTrue(result);
    }
    @Test
    void loadInfoDoacoes() {
        TestingDoacoesAdocoesClass testingDoacoesAdocoesClass = new TestingDoacoesAdocoesClass();
        boolean result = testingDoacoesAdocoesClass.loadInfoDoacoes();
        assertTrue(result);
    }
}