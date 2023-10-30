package com.example.gps_g21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class operacoesCalculadoraTest {

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
        assertEquals(operacoesCalculadora.differenceBetweenDates("02/05/2023", "10/05/2023"), 8);
        assertEquals(operacoesCalculadora.differenceBetweenDates("02/05/2023", "10/06/2023"), 39);
    }

    @Test
    void binaryToHexadecimal() {
        assertEquals(operacoesCalculadora.binaryToHexadecimal("11010101"), "D5");
        assertEquals(operacoesCalculadora.binaryToHexadecimal("1010111"), "57");
        assertEquals(operacoesCalculadora.binaryToHexadecimal("110101011"), "1AB");
        assertEquals(operacoesCalculadora.binaryToHexadecimal("10101011"), "AB");
    }

    @Test
    void hexadecimalToBinary() {
        assertEquals(operacoesCalculadora.hexadecimalToBinary("D5"), "11010101");
        assertEquals(operacoesCalculadora.hexadecimalToBinary("57"), "1010111");
        assertEquals(operacoesCalculadora.hexadecimalToBinary("1AB"), "110101011");
        assertEquals(operacoesCalculadora.hexadecimalToBinary("AB"), "10101011");
    }

    @Test
    void fibonacci() {
        assertEquals(operacoesCalculadora.fibonacci(1), 1);
        assertEquals(operacoesCalculadora.fibonacci(15), 610);
        assertEquals(operacoesCalculadora.fibonacci(-4), -1);
        assertEquals(operacoesCalculadora.fibonacci(-60), -1);
    }

    @Test
    void factorial() {
        assertEquals(operacoesCalculadora.factorial(4), 24);
        assertEquals(operacoesCalculadora.factorial(5), 120);
        assertEquals(operacoesCalculadora.factorial(-1), -1);
    }

    @Test
    void volumeOfCylinder() {
        assertEquals(operacoesCalculadora.volumeOfCylinder(2, 4), 50.27);
        assertEquals(operacoesCalculadora.volumeOfCylinder(6.4, 1.2), 154.42);
        assertEquals(operacoesCalculadora.volumeOfCylinder(6.4, -1), -1);
    }

    @Test
    void volumeOfCone() {
        assertEquals(operacoesCalculadora.volumeOfCone(4.5, 10.3), 218.42);
        assertEquals(operacoesCalculadora.volumeOfCone(6.4, 1.2), 51.47);
        assertEquals(operacoesCalculadora.volumeOfCone(6.4, -1), -1);
        assertEquals(operacoesCalculadora.volumeOfCone(-4   , -1), -1);
    }
}