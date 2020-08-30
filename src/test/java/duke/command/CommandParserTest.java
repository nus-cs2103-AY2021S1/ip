package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import duke.exception.DateParseException;
import duke.exception.DukeException;
import duke.exception.IncompleteTaskException;
import duke.exception.InvalidTaskException;
import duke.exception.UnknownCommandException;

public class CommandParserTest {
    @Test
    public void parseTodoCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("todo exercise") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseIncompleteTodoCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("todo");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseEventCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("event meeting /at 2020-08-30") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseIncompleteEventCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("event meeting");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseInvalidDateEventCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("event meeting /at 2020-8-9");
        } catch (DukeException e) {
            assertTrue(e instanceof DateParseException);
        }
    }

    public void parseDeadlineCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("deadline homework /by 2020-09-23") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseIncompleteDeadlineCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("deadline homework");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseInvalidDateDeadlineCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("deadline meeting /by 2020-8-9");
        } catch (DukeException e) {
            assertTrue(e instanceof DateParseException);
        }
    }

    @Test
    public void parseDeleteCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("delete 3") instanceof DeleteCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseInvalidDeleteCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("delete");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskException);
        }
    }

    @Test
    public void parseDoneCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("done 2") instanceof DoneCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseInvalidDoneCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("done");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskException);
        }
    }

    @Test
    public void parseTodayCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("today") instanceof TodayCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseListCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("list") instanceof ListCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseExitCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("bye") instanceof ExitCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseInvalidCommand() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("lorem ipsum");
        } catch (DukeException e) {
            assertTrue(e instanceof UnknownCommandException);
        }
    }
}
