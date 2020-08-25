package duke;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_validCommand_success() throws DukeException {
        assertTrue(Parser.parseCommand("todo read book") instanceof TodoCommand);
        assertTrue(Parser.parseCommand("deadline return book /by 2020-08-28") instanceof DeadlineCommand);
        assertTrue(Parser.parseCommand("event birthday /at 2020-08-31") instanceof EventCommand);
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
        assertTrue(Parser.parseCommand("done 1") instanceof DoneCommand);
        assertTrue(Parser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parseCommand("blah") instanceof InvalidCommand);
    }

    @Test
    public void parseCommand_missingDescription_exceptionThrown() {
        String message = "OOPS!!! Description of a task cannot be empty :(\n";
        try {
            Parser.parseCommand("todo");
            fail();
        } catch (DukeException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void parseCommand_missingTaskNumber_exceptionThrown() {
        String message = "Missing task number! "
                + "Please ensure to key in the task number :)\n";
        try {
            Parser.parseCommand("done");
            fail();
        } catch (DukeException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void parseCommand_missingKeyword_exceptionThrown() {
        String message = "Please indicate a deadline using the \"/by\" keyword.\n";
        try {
            Parser.parseCommand("deadline return book");
            fail();
        } catch (DukeException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidDateFormat_exceptionThrown() {
        String message = "Invalid date format! "
                + "Please use the proper date format i.e. yyyy-MM-dd\n";
        try {
            Parser.parseCommand("event birthday /at next Monday");
            fail();
        } catch (DukeException e) {
            assertEquals(message, e.getMessage());
        }
    }
}