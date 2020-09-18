package duke.command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.data.DukeTaskListSideEffects;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;

public class DeleteCommandTest {

    private final DeleteCommand command = new DeleteCommand();

    private final DukeStub dukeStub = new DukeStub();

    private DukeTaskListSideEffects taskListSideEffects = DukeTaskListSideEffects.getInstance();

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

        try {
            command.execute(normalInput, dukeStub);

            assertEquals(true, taskListSideEffects.deleteTask);

            taskListSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        String invalidInput = "hahaha";

        Exception exception = assertThrows(
                InvalidIndexException.class, () -> command.execute(invalidInput, dukeStub));

        String errMessage = ExceptionMessage.getInvalidIndexMessage(invalidInput);

        assertEquals(errMessage, exception.getMessage());
    }
}
