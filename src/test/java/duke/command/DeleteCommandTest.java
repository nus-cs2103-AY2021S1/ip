package duke.command;

import duke.DukeStub;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {

    private final DeleteCommand command = new DeleteCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new DeleteCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskDeleted() {
        String normalInput = "1";
        Task taskToBeDeleted = dukeStub.taskList.tasks.get(0);

        try {
            command.execute(normalInput, dukeStub);
            if (dukeStub.taskList.tasks.contains(taskToBeDeleted)) {
                fail("task not deleted");
            }
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        String invalidInput = "5";

        Exception exception = assertThrows(InvalidIndexException.class,
                () -> command.execute(invalidInput, dukeStub));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nSorry " + invalidInput + " is not a valid index\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }
}
