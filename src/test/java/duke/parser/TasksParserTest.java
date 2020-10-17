package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


public class TasksParserTest extends ParserTest {

    /**
     * Tests if TodoParser parses an invalid todocommand properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidTodoCommand_correctOutput() {
        TodoParser invalidTodoParser = new TodoParser("todo ");
        try {
            String invalidOutput = invalidTodoParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Hey! Your Todo is empty >:(", e.getMessage());
        }
    }

    /**
     * Tests if TodoParser parses a valid todocommand properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validTodoCommand_correctOutput() {
        TodoParser validTodoParser = new TodoParser("todo housework");
        try {
            String validOutput = validTodoParser.checkIfValid();
            Todo expectedTodoOutput = new Todo("housework");
            assertEquals(expectedTodoOutput.toString(), validOutput);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests if DeadlineParser parses an invalid deadline command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidDeadlineCommand_correctOutput() {
        DeadlineParser invalidDeadlineParser = new DeadlineParser("deadline assignment /by ");
        try {
            String validOutput = invalidDeadlineParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Oi, when is this deadline due??", e.getMessage());
        }
    }

    /**
     * Tests if DeadlineParser parses a valid deadline command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validDeadlineCommand_correctOutput() {
        DeadlineParser validDeadlineParser = new DeadlineParser("deadline assignment /by 2020-09-09");
        try {
            String validOutput = validDeadlineParser.checkIfValid();
            Deadline expectedDeadlineOutput = new Deadline("assignment", "2020-09-09");
            assertEquals(expectedDeadlineOutput.toString(), validOutput);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests if EventParser parses an invalid event command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidEventCommand_correctOutput() {
        EventParser invalidEventParser = new EventParser("event conference /at 2020-13-13");
        try {
            String invalidOutput = invalidEventParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Please check your date! It is clearly not realistic >:(", e.getMessage());
        }
    }

    /**
     * Tests if EventParser parses a valid event command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validEventCommand_correctOutput() {
        EventParser validEventParser = new EventParser("event conference /at 2020-11-12");
        try {
            String validOutput = validEventParser.checkIfValid();
            Event expectedEventOutput = new Event("conference", "2020-11-12");
            assertEquals(expectedEventOutput.toString(), validOutput);
        } catch (Exception e) {
            fail();
        }
    }
}
