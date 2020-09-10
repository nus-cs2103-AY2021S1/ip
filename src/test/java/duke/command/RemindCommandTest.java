package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;
import duke.stub.task.TaskListStub;
import duke.task.TaskList;

public class RemindCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        String actual = RemindCommand.execute("remind 5", taskListStub);

        String expected = "These are the tasks that are due within 5 days:\n"
                + "8.[X] event this is an event stub";
        assertEquals(expected, actual);
    }
}
