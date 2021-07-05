package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][×] a  <-", new Todo("a").toString());
    }
}