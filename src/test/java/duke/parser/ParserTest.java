package duke.parser;

import duke.command.Command;
import duke.command.CommandException;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeParseException;

public class ParserTest {
    @Test
    public void parseEvent_noArguments_exceptionThrown() {
        try {
            Parser.parse("event meeting /at 07/02/2020 2200");
        } catch (DateTimeParseException e) {
            assertEquals("Invalid date/time! Here's an example format Eg. 2019-12-12 1800", e.getMessage());
        }
    }

    @Test
    public void parseDelete_noArguments_exceptionThrown() {
        try {
            Parser.parse("delete");
        } catch (NumberFormatException e) {
            assertEquals("I need the task index too! Eg. delete 1", e.getMessage());
        }
    }

    @Test
    public void parseBye_validCommand_isExit() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c.isExit());
    }

    @Test
    public void parseUnknownCommand_invalidCommand() throws DukeException {
        Command c = Parser.parse("lalalaala");
        assertTrue(c instanceof CommandException);
    }

}