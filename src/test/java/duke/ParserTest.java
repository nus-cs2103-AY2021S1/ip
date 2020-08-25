package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parseCommand_Parser_Test() {
        try {
            Parser.parseCommand("deadline");
        } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    void stopProgram_Parser_Test() {
        assertTrue(Parser.stopProgram("bye"));
    }
}