package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.ViewallCommand;
import exception.DeadlineInvalidUsageException;
import exception.EventInvalidUsageException;
import exception.InvalidUsageException;
import exception.UnknownCommandException;
import exception.ViewallInvalidUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    void unknownCommand_exceptionThrown() {
        String input = "test";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof UnknownCommandException);
        }
    }

    @Test
    public void parseBye_success() {
        String input = "bye";
        try {
            assertTrue(Parser.parseCommand(input) instanceof ByeCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseList_success() {
        String input = "list";
        try {
            assertTrue(Parser.parseCommand(input) instanceof ListCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseDone_success() {
        String input = "done 1";
        try {
            assertTrue(Parser.parseCommand(input) instanceof DoneCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseDone_missingTaskNumber_exceptionThrown() {
        String input = "done";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidUsageException);
        }
    }

    @Test
    public void parseDone_invalidTaskNumber_exceptionThrown() {
        String input = "done asdf";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidUsageException);
        }
    }

    @Test
    public void parseDelete_success() {
        String input = "delete 1";
        try {
            assertTrue(Parser.parseCommand(input) instanceof DeleteCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseDelete_missingTaskNumber_exceptionThrown() {
        String input = "delete";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidUsageException);
        }
    }

    @Test
    public void parseDelete_invalidTaskNumber_exceptionThrown() {
        String input = "delete asdf";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidUsageException);
        }
    }

    @Test
    public void parseTodo_success() {
        String input = "todo return book";
        try {
            assertTrue(Parser.parseCommand(input) instanceof TodoCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseTodo_missingDescription_exceptionThrown() {
        String input = "todo";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidUsageException);
        }
    }

    @Test
    public void parseEvent_success() {
        String input = "event my birthday /at 2020-08-25";
        try {
            assertTrue(Parser.parseCommand(input) instanceof EventCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseEvent_missingDate_exceptionThrown() {
        String input = "event my birthday /at";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof EventInvalidUsageException);
        }
    }

    @Test
    public void parseEvent_wrongDateSeparator_exceptionThrown() {
        String input = "event my birthday /by 2020-08-25";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof EventInvalidUsageException);
        }
    }

    @Test
    public void parseEvent_wrongDateFormat_exceptionThrown() {
        String input = "event my birthday /at 25/08/2020";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof EventInvalidUsageException);
        }
    }

    @Test
    public void parseDeadline_success() {
        String input = "deadline finish level 6 /by 2020-08-25";
        try {
            assertTrue(Parser.parseCommand(input) instanceof DeadlineCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseDeadline_missingDate_exceptionThrown() {
        String input = "deadline finish level 6 /by";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof DeadlineInvalidUsageException);
        }
    }

    @Test
    public void parseDeadline_wrongDateSeparator_exceptionThrown() {
        String input = "deadline finish level 6 /at";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof DeadlineInvalidUsageException);
        }
    }

    @Test
    public void parseDeadline_wrongDateFormat_exceptionThrown() {
        String input = "deadline finish level 6 /at 19/08/2020";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof DeadlineInvalidUsageException);
        }
    }

    @Test
    public void parseViewAll_success() {
        String input = "viewall 2020-08-20";
        try {
            assertTrue(Parser.parseCommand(input) instanceof ViewallCommand);
        } catch (Exception ex) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseViewAll_wrongDateFormat_exceptionThrown() {
        String input = "viewall 2020-08-20";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof ViewallInvalidUsageException);
        }
    }

    @Test
    public void parseViewAll_missingDate_exceptionThrown() {
        String input = "viewall";
        try {
            Command c = Parser.parseCommand(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof ViewallInvalidUsageException);
        }
    }
}