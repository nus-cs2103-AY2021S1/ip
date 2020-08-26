package test.java.duke;

import main.java.duke.Parser;
import main.java.duke.command.Command;
import main.java.duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void testParseCommand() {
        try {
            Parser.parseCommand("event read book /at 2020-10-10 20:00");
            assertTrue(true);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testParseError() {
        try {
            Parser.parseCommand("even read book 2020-10-10 20:00");
            fail();
        } catch (DukeException e) {
            assertTrue(true);
        }
    }

}