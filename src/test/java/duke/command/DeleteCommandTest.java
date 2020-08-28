package duke.command;

import duke.exception.InvalidCommandException;
import duke.stub.task.DeadlineStub;
import duke.stub.task.TaskListStub;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        int len = taskListStub.size();
        String actual = DeleteCommand.execute("delete 1", taskListStub);
        String expected =
                "Noted. I've removed this task:\n"
                + "  " + new DeadlineStub() + "\n"
                + "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
        assertEquals(expected, actual);
    }
}
