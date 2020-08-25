package duke;

import duke.command.*;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void parseListCommand_lowercaseTest() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseListCommand_nonLowercaseTest() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseListCommand_invalidArgumentExceptionThrow() {
        try {
            Command c = Parser.parse("list blah");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The list command does not take any additional argument(s).", e.getMessage());
        }
    }

    @Test
    void parseByeCommand() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof ByeCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseByeCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("bye blah");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The bye command does not take any additional argument(s).", e.getMessage());
        }
    }

    @Test
    void parseDoneCommandTest() {
        try {
            Command c = Parser.parse("done 1");
            assertTrue(c instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseDoneCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("done");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The done command requires the index of a task.", e.getMessage());
        }
    }

    @Test
    void parseShowCommandTest() {
        try {
            Command c = Parser.parse("show 2020-08-25");
            assertTrue(c instanceof ShowCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseShowCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("show");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The show command requires a date in yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    void parseDeleteCommandTest() {
        try {
            Command c = Parser.parse("delete 1");
            assertTrue(c instanceof DeleteCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseDeleteCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("delete");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The delete command requires the index of a task.", e.getMessage());
        }
    }

    @Test
    void parseTodoCommandTest() {
        try {
            Command c = Parser.parse("todo reading");
            assertTrue(c instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseTodoCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("todo");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parseEventCommandTest() {
        try {
            Command c = Parser.parse("event reading /at 2020-08-24");
            assertTrue(c instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseEventCommand_invalidArgumentExceptionThrown1() {
        try {
            Command c = Parser.parse("event");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of an event cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parseEventCommand_invalidArgumentExceptionThrown2() {
        try {
            Command c = Parser.parse("event reading");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please specify a date for the event.", e.getMessage());
        }
    }

    @Test
    void parseEventCommand_invalidArgumentExceptionThrown3() {
        try {
            Command c = Parser.parse("event reading /at");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please specify a date for the event.", e.getMessage());
        }
    }

    @Test
    void parseDeadlineCommandTest() {
        try {
            Command c = Parser.parse("deadline reading /by 2020-08-24");
            assertTrue(c instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseDeadlineCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("deadline");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parseDeadlineCommand_invalidArgumentExceptionThrown2() {
        try {
            Command c = Parser.parse("deadline reading");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please specify a due date for the deadline.", e.getMessage());
        }
    }

    @Test
    void parseDeadlineCommand_invalidArgumentExceptionThrown3() {
        try {
            Command c = Parser.parse("deadline reading /by");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please specify a due date for the deadline.", e.getMessage());
        }
    }

    @Test
    void parsePendingCommandTest() {
        try {
            Command c = Parser.parse("pending");
            assertTrue(c instanceof PendingCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parsePendingCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("pending 123");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The pending command does not take any additional argument(s).", e.getMessage());
        }
    }

    @Test
    void parseCompletedCommandTest() {
        try {
            Command c = Parser.parse("completed");
            assertTrue(c instanceof CompletedCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCompletedCommand_invalidArgumentExceptionThrown() {
        try {
            Command c = Parser.parse("completed 123");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The completed command does not take any additional argument(s).", e.getMessage());
        }
    }
}
