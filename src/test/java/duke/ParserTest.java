package duke;

import duke.command.Command;
import duke.command.InvalidCommand;

import duke.exception.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseDone_noArguments_exceptionThrown() {
        DukeException e = assertThrows(DukeException.class, () -> Parser.parse("done"));
        String expectedMessage = "Invalid arguments provided!";
        assertEquals(e.getMessage(), expectedMessage);
    }

    @Test
    public void parseDelete_noArguments_exceptionThrown() {
        DukeException e = assertThrows(DukeException.class, () -> Parser.parse("delete"));
        String expectedMessage = "Invalid arguments provided!";
        assertEquals(e.getMessage(), expectedMessage);
    }

    @Test
    public void parseBye_validCommand_isExit() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c.isExit());
    }

    @Test
    public void parseUnknownCommand_invalidCommand() throws DukeException {
        Command c = Parser.parse("nvieovne");
        assertTrue(c instanceof InvalidCommand);
    }

}
