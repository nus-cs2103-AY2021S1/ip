package duke.command;

import duke.stub.task.TaskListStub;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    @Test
    public void execute() {
        TaskList taskListStub = new TaskListStub();
        String actual = FindCommand.execute("find event stub", taskListStub);
        String expected =
                "Here are the matching tasks in your list:\n"
                + "8.[✘] event this is an event stub";
        assertEquals(expected, actual);
    }
}
