package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;
import duke.stub.task.TaskListStub;
import duke.stub.task.TaskStub;
import duke.task.TaskList;

public class TagCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        taskListStub.add(new TaskStub("abc"), false);
        String actual = TagCommand.execute("tag 10 tag1 tag2 tag3", taskListStub);

        String expected = "I have added the tags to the following task:\n" + "  [X] abc";
        assertEquals(expected, actual);
    }
}
