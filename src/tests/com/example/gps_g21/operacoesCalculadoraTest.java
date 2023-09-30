package com.example.gps_g21;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class operacoesCalculadoraTest {

    @BeforeEach
    void setUp() {
        System.out.println("Before each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @Test
    void operacoesBasicas() {
        assertEquals(operacoesCalculadora.operacoesBasicas("2+2"), 4);
        assertEquals(operacoesCalculadora.operacoesBasicas("2-2"), 0);
        assertEquals(operacoesCalculadora.operacoesBasicas("2*2"), 4);
        assertEquals(operacoesCalculadora.operacoesBasicas("2/2"), 1);
        assertEquals(operacoesCalculadora.operacoesBasicas("2^2"), 4);
        assertEquals(operacoesCalculadora.operacoesBasicas("2+2+2"), -1);
    }

    @Test
    void addSubtractDaysToDate() {
        assertEquals(operacoesCalculadora.addSubtractDaysToDate("01/01/2023", 2), "03/01/2023");
        assertEquals(operacoesCalculadora.addSubtractDaysToDate("01/01/2023", -2), "30/12/2022");
        assertEquals(operacoesCalculadora.addSubtractDaysToDate("01/01/2023", 0), "01/01/2023");
    }

    @Test
    void differenceBetweenDates() {
    }

    @Test
    void binaryToHexadecimal() {
    }

    @Test
    void hexadecimalToBinary() {
    }

    @Test
    void fibonacci() {
    }

    @Test
    void factorial() {
    }

    @Test
    void volumeOfCylinder() {
    }

    @Test
    void volumeOfCone() {
    }
}