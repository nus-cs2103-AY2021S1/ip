package duke.test;

import static org.junit.jupiter.api.Assertions.*;

import duke.util.Parser;

class ParserTest {

    @org.junit.jupiter.api.Test
    void getTaskDescription() {
        assertEquals("2 3 4", Parser.getTaskDescription(
                new String[] {"1", "2", "3", "4"}));
    }

    @org.junit.jupiter.api.Test
    void parse() {
        // err ...
    }
}