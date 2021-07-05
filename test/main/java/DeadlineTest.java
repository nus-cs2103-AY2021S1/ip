package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testGetTime() {
        assertEquals("2020-08-01", (new Deadline("a", "2020-08-01").getTime()));
    }

    @Test
    void testToString() {
        assertEquals("[D][√] a(by Aug 01 2020)  <-", (new Deadline(true, "a", "2020-08-01")).toString());
    }
}