package duke.command;

import duke.parser.EventParser;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.parser.DeadlineParser;
import duke.parser.TodoParser;
import duke.task.Deadline;
import duke.task.Todo;

public class TasksCommandTest extends CommandTest {
    /**
     * Tests if the TodoCommand works as expected.
     * @throws Exception if the TodoCommand does not produce the expected output.
     */
    @Test
    public void testTodoCommandExecution() throws Exception {
        setLines();
        TodoParser invalidTodoParser = new TodoParser("todo ");
        TodoCommand invalidTodoCommand = new TodoCommand(lines, invalidTodoParser);
        String invalidOutcome = invalidTodoCommand.execute();
        if (!invalidOutcome.equals(Ui.handleDukeException(new DukeException("Hey! Your Todo is empty >:(")))) {
            throw new Exception("Todo command did not output the expected error message");
        }
        resetLines();
        setLines();
        TodoParser validTodoParser = new TodoParser("todo sleep");
        TodoCommand validTodoCommand = new TodoCommand(lines, validTodoParser);
        String validOutcome = validTodoCommand.execute();
        Todo expectedTodo = new Todo("sleep");
        if (!validOutcome.equals(Ui.addedTask(expectedTodo.toString(), 4))) {
            System.out.println("Todo command failed to create the expected output message");
        }
        System.out.println("All tests passed");
        resetLines();
    }

    /**
     * Tests if the Deadline command works as expected.
     * @throws Exception If the Deadline command does not work as expected.
     */
    @Test
    public void testDeadlineCommandExecution() throws Exception {
        setLines();
        DeadlineParser invalidDeadlineParser = new DeadlineParser("deadline homework /by 2020-31-31");
        DeadlineCommand invalidDeadlineCommand = new DeadlineCommand(lines, invalidDeadlineParser);
        String invalidOutcome = invalidDeadlineCommand.execute();
        if (!invalidOutcome.equals(Ui.handleDukeException(
                new DukeException("Please check your date! It is clearly not realistic >:(")))) {
            throw new Exception("invalid deadline command failed to output the correct error message");
        }
        resetLines();
        setLines();
        DeadlineParser validDeadlineParser = new DeadlineParser("deadline homework /by 2020-09-09");
        DeadlineCommand validDeadlineCommand = new DeadlineCommand(lines, validDeadlineParser);
        String validOutcome = validDeadlineCommand.execute();
        Deadline expectedDeadline = new Deadline("homework", "2020-09-09");
        if (!validOutcome.equals(Ui.addedTask(expectedDeadline.toString(), 4))) {
            throw new Exception("Valid Deadline command failed to achieve the desired output / outcome");
        }
        System.out.println("All tests passed");
        resetLines();
    }

    /**
     * Tests if the event command works as expected
     * @throws Exception
     */
    @Test
    public void testEventCommandExecution() throws Exception {
        setLines();
        EventParser invalidEventParser = new EventParser("event Career-Fair /at");
        EventCommand invalidEventCommand = new EventCommand(lines, invalidEventParser);
        String invalidOutcome = invalidEventCommand.execute();
        System.out.println(invalidOutcome);
        if (!invalidOutcome.equals(Ui.handleDukeException(new DukeException("Oi, when is this event at??")))) {
            throw new Exception("invalid event command failed to output the correct error message");
        }
        resetLines();
        setLines();
        EventParser validEventParser = new EventParser("event Career-Fair /at 2020-10-10");
        EventCommand validEventCommand = new EventCommand(lines, validEventParser);
        String validOutcome = validEventCommand.execute();
        Event expectedEvent = new Event("Career-Fair", "2020-10-10");
        if (!validOutcome.equals(Ui.addedTask(expectedEvent.toString(), 4))) {
            throw new Exception("valid event command failed to output the expected output");
        }
        System.out.println("All tests passed");
        resetLines();
    }

}
