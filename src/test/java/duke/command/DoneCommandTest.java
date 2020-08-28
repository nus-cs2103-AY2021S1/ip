package duke.command;

import duke.exception.InvalidCommandException;
import duke.stub.task.TaskListStub;
import duke.stub.task.TodoStub;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        String actual = DoneCommand.execute("done 3", taskListStub);
        TodoStub todoStub = new TodoStub();
        todoStub.markAsDone();
        String expected = "Nice! I've marked this task as done\n  " + todoStub.toString();
        assertEquals(expected, actual);
    }
}