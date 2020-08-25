package taskbot.parser;

import org.junit.jupiter.api.Test;

import taskbot.command.TodoCommand;
import taskbot.command.DeadlineCommand;
import taskbot.command.EventCommand;
import taskbot.command.ListCommand;
import taskbot.command.UpcomingCommand;
import taskbot.command.FindCommand;
import taskbot.command.DeleteCommand;
import taskbot.command.DoneCommand;
import taskbot.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the various commands that Parser handles.
 */
public class ParserTest {
    private String testCommand;
    @Test
    public void testParseTodo() {
        try {
            testCommand = "todo todoTask";
            assertEquals(new TodoCommand("todoTask"), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseDeadline() {
        try {
            testCommand = "deadline deadlineTask /by 2020-08-03 0845";
            assertEquals(new DeadlineCommand("deadlineTask /by 2020-08-03 0845"),
                    Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseEvent() {
        try {
            testCommand = "event eventTask /at 2020-08-03 0845";
            assertEquals(new EventCommand("eventTask /at 2020-08-03 0845"),
                    Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseList() {
        try {
            testCommand = "list";
            assertEquals(new ListCommand(), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseUpcoming() {
        try {
            testCommand = "upcoming 2";
            assertEquals(new UpcomingCommand(2), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseFind() {
        try {
            testCommand = "find book";
            assertEquals(new FindCommand("book"),
                    Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseDone() {
        try {
            testCommand = "done 2";
            // Index of tasks list will be 2 - 1 = 1
            assertEquals(new DoneCommand(1), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseDelete() {
        try {
            testCommand = "delete 2";
            // Index of tasks list will be 2 - 1 = 1
            assertEquals(new DeleteCommand(1), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseExit() {
        try {
            testCommand = "bye";
            assertEquals(new ExitCommand(), Parser.parse(testCommand));
        } catch (Exception e) {
            fail();
        }
    }
}
