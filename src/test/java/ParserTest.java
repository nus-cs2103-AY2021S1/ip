import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Commands;
import duke.Parser;
import exception.DukeErrorException;

public class ParserTest {

    @Test
    public void unknownCommand_exceptionThrown() {
        String input = "TEST";
        try {
            Commands cmd = Parser.processInput(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void parseInput_invalidInputFormat_exceptionThrown() {
        String input = "test";
        try {
            Commands cmd = Parser.processCommand(input.split(" ", 2));
        } catch (Exception ex) {
            assertTrue(ex instanceof DukeErrorException);
        }
    }

}
