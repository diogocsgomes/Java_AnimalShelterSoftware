package com.example.gps_g21.TestMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingVoluntariosClassTest {
    @Test
    void loadInfoVoluntarios() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntarios();
        assertTrue(result);
    }

    @Test
    void loadInfoVoluntariosByName() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntariosByName();
        assertTrue(result);
    }


    @Test
    void loadInfoVoluntariosByRole() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntariosByRole();
        assertTrue(result);
    }

    @Test
    void loadInfoVoluntariosById() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntariosById();
        assertTrue(result);
    }

    @Test
    void loadInfoVoluntariosByPhone() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntariosByPhone();
        assertTrue(result);
    }

    @Test
    void loadInfoVoluntariosByNif() {
        TestingVoluntariosClass testingVoluntariosClass = new TestingVoluntariosClass();
        boolean result = testingVoluntariosClass.loadInfoVoluntariosByNif();
        assertTrue(result);
    }
}