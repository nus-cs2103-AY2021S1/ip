package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.Ui;
import duke.parser.DeadlineParser;
import duke.parser.EventParser;
import duke.parser.TodoParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class TasksCommandTest extends CommandTest {
    /**
     * Tests if TodoCommand provides the correct output given a TodoParser that parsed an invalid todotask command.
     */
    @Test
    public void execute_invalidTodoCommand_correctOutput() {
        setLines();
        try {
            TodoParser invalidTodoParser = new TodoParser("todo ");
            TodoCommand invalidTodoCommand = new TodoCommand(lines, invalidTodoParser);
            String invalidOutcome = invalidTodoCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("Hey! Your Todo is empty >:(")), invalidOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if TodoCommand provides the correct output given a TodoParser that parsed an valid todotask command.
     */
    @Test
    public void execute_validTodoCommand_correctOutput() {
        setLines();
        try {
            TodoParser validTodoParser = new TodoParser("todo sleep");
            TodoCommand validTodoCommand = new TodoCommand(lines, validTodoParser);
            String validOutcome = validTodoCommand.execute();
            Todo expectedTodo = new Todo("sleep");
            assertEquals(Ui.addedTask(expectedTodo.toString(), 4), validOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if DeadlineCommand provides the correct output given a DeleteParser that parsed an invalid
     * deadline task command.
     */
    @Test
    public void execute_invalidDeadlineCommand_correctOutput() {
        setLines();
        try {
            DeadlineParser invalidDeadlineParser = new DeadlineParser("deadline homework /by 2020-31-31");
            DeadlineCommand invalidDeadlineCommand = new DeadlineCommand(lines, invalidDeadlineParser);
            String invalidOutcome = invalidDeadlineCommand.execute();
            assertEquals(Ui.handleDukeException(
                    new DukeException("Please check your date! It is clearly not realistic >:(")), invalidOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if DeadlineCommand provides the correct output given a DeleteParser that parsed a valid
     * deadline task command.
     */
    @Test
    public void execute_validDeadlineCommand_correctOutput() {
        setLines();
        try {
            DeadlineParser validDeadlineParser = new DeadlineParser("deadline homework /by 2020-09-09");
            DeadlineCommand validDeadlineCommand = new DeadlineCommand(lines, validDeadlineParser);
            String validOutcome = validDeadlineCommand.execute();
            Deadline expectedDeadline = new Deadline("homework", "2020-09-09");
            assertEquals(Ui.addedTask(expectedDeadline.toString(), 4), validOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if EventCommand provides the correct output given an EventParser that parsed an invalid event task command.
     */
    @Test
    public void execute_invalidEventCommand_correctOutput() {
        setLines();
        try {
            EventParser invalidEventParser = new EventParser("event Career-Fair /at");
            EventCommand invalidEventCommand = new EventCommand(lines, invalidEventParser);
            String invalidOutcome = invalidEventCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("Oi, when is this event at??")), invalidOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if EventCommand provides the correct output given an EventParser that parsed a valid event task command.
     */
    @Test
    public void execute_validEventCommand_correctOutput() {
        setLines();
        try {
            EventParser validEventParser = new EventParser("event Career-Fair /at 2020-10-10");
            EventCommand validEventCommand = new EventCommand(lines, validEventParser);
            String validOutcome = validEventCommand.execute();
            Event expectedEvent = new Event("Career-Fair", "2020-10-10");
            assertEquals(Ui.addedTask(expectedEvent.toString(), 4), validOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

}
