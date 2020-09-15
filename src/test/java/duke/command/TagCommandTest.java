package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;
import duke.response.Response;
import duke.stub.task.TaskListStub;
import duke.stub.task.TaskStub;
import duke.task.TaskList;

public class TagCommandTest {
    @Test
    public void execute() throws InvalidCommandException {
        TaskList taskListStub = new TaskListStub();
        taskListStub.add(new TaskStub("abc"), false);
        Response response = TagCommand.execute("tag 10 tag1 tag2 tag3", taskListStub);

        String expected = "I have added the tags to the following task:\n" + "  [X] abc";
        assertEquals(expected, response.getMessage());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        TaskList taskListStub = new TaskListStub();
        assertThrows(InvalidCommandException.class, () -> {
            TagCommand.execute("tag 10a tag1 tag2 tag3", taskListStub);
        });
    }
}
