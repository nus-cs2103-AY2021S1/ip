package duke.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void incorrectParse() {
        try {
            Command command = Parser.parse("some funny unknown command");
            fail("Incorrect message in parser should have thrown exception");
        } catch (DukeException e) {
            return;
        }
    }

    @Test
    public void correctParse() {
        try {
            Command command = Parser.parse("event description /at 22/2/2012 1630");
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
