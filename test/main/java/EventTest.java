package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testGetTime() {
        assertEquals("2020-08-01", (new Event("a", "2020-08-01").getTime()));
    }

    @Test
    void testToString() {
        assertEquals("[E][√] a(on Aug 01 2020)  <-", (new Event(true, "a", "2020-08-01")).toString());
    }
}