package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.task.Task;

public class DoneCommandTest {

    private final DoneCommand command = new DoneCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new DoneCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void executeTest() {
        String input = "1";
        Task taskToBeMarked = dukeStub.getTaskList().getTask(0);

        try {
            command.execute(input, dukeStub);

            assertEquals(true, taskToBeMarked.isTaskDone());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
