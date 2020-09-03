package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.data.DukeTaskListSideEffects;
import duke.exception.ExceptionMessage;
import duke.exception.NoDescriptionException;
import duke.ui.UiSideEffects;


public class TodoCommandTest {

    private final TodoCommand command = new TodoCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    private DukeTaskListSideEffects taskListSideEffects = DukeTaskListSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new TodoCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskAdded() {
        String normalInput = "read book";

        try {
            command.execute(normalInput, dukeStub);

            assertEquals(true, taskListSideEffects.addTask);
            assertEquals(true, uiSideEffects.uiReportNewTask);

            taskListSideEffects.reset();
            uiSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        String invalidInput = "";

        Exception exception = assertThrows(
                NoDescriptionException.class, () -> command.execute(invalidInput, dukeStub));

        String errMessage = ExceptionMessage.TODO_NO_DESCRIPTION_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }
}
