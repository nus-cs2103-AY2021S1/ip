package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    void parseCommand_parser_test() {
        try {
            Parser.parseCommand("deadline");
        } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    void stopProgram_parser_test() {
        assertTrue(Parser.stopProgram("bye"));
    }
}
