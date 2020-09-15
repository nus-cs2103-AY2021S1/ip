package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;
import duke.response.Response;
import duke.stub.task.TaskListStub;
import duke.stub.task.TodoStub;
import duke.task.TaskList;

public class DoneCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        Response response = DoneCommand.execute("done 3", taskListStub);
        TodoStub todoStub = new TodoStub();
        todoStub.markAsDone();
        String expected = "Nice! I've marked this task as done\n  " + todoStub.toString();
        assertEquals(expected, response.getMessage());
    }
}
