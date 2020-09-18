package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.data.DukeTaskListSideEffects;
import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;
import duke.ui.UiSideEffects;

public class EventCommandTest {

    private final EventCommand command = new EventCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    private DukeTaskListSideEffects taskListSideEffects = DukeTaskListSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new EventCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskAdded() {
        String normalInput = "return book /at 12:33:01";

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
        String invalidInput = "return book";

        Exception exception = assertThrows(
                IncorrectFormatException.class, () -> command.execute(invalidInput, dukeStub));

        String errMessage = ExceptionMessage.EVENT_INCORRECT_FORMAT_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }
}
