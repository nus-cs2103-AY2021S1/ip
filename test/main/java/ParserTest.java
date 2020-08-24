package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseTask_todoTask_success() {
        assertEquals((new Todo("a")).toString(), (new Parser()).parseTask("todo a").toString());
    }

    @Test
    void parseTask_eventTask_success() {
        assertEquals((new Event("a", "2020-08-01")).toString(), (new Parser()).parseTask("event a/2020-08-01").toString());
    }

    @Test
    void parseTask_deadlineTask_success() {
        assertEquals((new Deadline("a", "2020-08-01")).toString(), (new Parser()).parseTask("deadline a/2020-08-01").toString());
    }
}