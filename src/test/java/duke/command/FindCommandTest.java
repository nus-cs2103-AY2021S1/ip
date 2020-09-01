package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.stub.task.TaskListStub;
import duke.task.TaskList;

public class FindCommandTest {
    @Test
    public void execute() {
        TaskList taskListStub = new TaskListStub();
        String actual = FindCommand.execute("find event stub", taskListStub);
        String expected =
                "Here are the matching tasks in your list:\n"
                + "8.[\u2718] event this is an event stub";
        assertEquals(expected, actual);
    }
}
