package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DateParseException;
import duke.exception.DukeException;
import duke.exception.IncompleteTaskException;
import duke.exception.InvalidTaskException;
import duke.exception.UnknownCommandException;

public class CommandParserTest {
    @Test
    public void parseCommand_addTaskCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("todo exercise") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_incompleteTodoDescription_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("todo");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseCommand_validEventCreated() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("event meeting /at 2020-08-30") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_incompleteEventDescription_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("event meeting");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseCommand_invalidEventDate_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("event meeting /at 2020-8-9");
        } catch (DukeException e) {
            assertTrue(e instanceof DateParseException);
        }
    }

    @Test
    public void parseCommand_validDeadlineCreated() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("deadline homework /by 2020-09-23") instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_incompleteDeadlineDescription_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("deadline homework");
        } catch (DukeException e) {
            assertTrue(e instanceof IncompleteTaskException);
        }
    }

    @Test
    public void parseCommand_invalidDeadlineDate_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("deadline meeting /by 2020-8-9");
        } catch (DukeException e) {
            assertTrue(e instanceof DateParseException);
        }
    }

    @Test
    public void parseCommand_deleteCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("delete 3") instanceof DeleteCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_missingtaskIndex_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("delete");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskException);
        }
    }

    @Test
    public void parseCommand_doneCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("done 3") instanceof DoneCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_invalidtaskIndex_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("done a");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskException);
        }
    }

    @Test
    public void parseCommand_todayCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("today") instanceof TodayCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_listCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("list") instanceof ListCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_exitCommand() {
        CommandParser parser = new CommandParser();
        try {
            assertTrue(parser.parseCommand("bye") instanceof ExitCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseCommand_invalidComand_exceptionThrown() {
        CommandParser parser = new CommandParser();
        try {
            parser.parseCommand("lorem ipsum");
        } catch (DukeException e) {
            assertTrue(e instanceof UnknownCommandException);
        }
    }
}
