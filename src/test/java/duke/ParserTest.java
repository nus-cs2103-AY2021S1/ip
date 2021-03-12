package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.logic.Parser;

public class ParserTest {
    @Test
    public void parseCommand_validCommand_success() throws DukeException {
        assertTrue(Parser.parseCommand("todo read book") instanceof TodoCommand);
        assertTrue(Parser.parseCommand("deadline return book /by 30/9/2020") instanceof DeadlineCommand);
        assertTrue(Parser.parseCommand("event birthday /at 30/9/2020") instanceof EventCommand);
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
        assertTrue(Parser.parseCommand("done 1") instanceof DoneCommand);
        assertTrue(Parser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parseCommand("blah") instanceof InvalidCommand);
    }

    @Test
    public void parseCommand_missingDescription_exceptionThrown() {
        String message = "OOPS!!! Description of a todo cannot be empty :(\n";
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
        String message = "Please indicate a date using the \"/by\" keyword.\n";
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
                + "Please use the proper date format i.e. dd/MM/yyyy\n";
        try {
            Parser.parseCommand("event birthday /at next Monday");
            fail();
        } catch (DukeException e) {
            assertEquals(message, e.getMessage());
        }
    }
}
