package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;
import duke.response.Response;
import duke.stub.task.TaskListStub;
import duke.task.TaskList;

public class RemindCommandTest {
    @Test
    public void execute_validIndex_success() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        Response response = RemindCommand.execute("remind 5", taskListStub);

        String expected = "These are the tasks that are due within 5 days:\n"
                + "8.[X] event this is an event stub";
        assertEquals(expected, response.getMessage());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        TaskList taskListStub = new TaskListStub();
        assertThrows(InvalidCommandException.class, () -> {
            RemindCommand.execute("remind -10", taskListStub);
        });
    }
}
