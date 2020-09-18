package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.ui.UiSideEffects;

public class UntagCommandTest {

    private final UntagCommand command = new UntagCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new UntagCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskDeleted() {
        String normalInput = "1";

        try {
            command.execute(normalInput, dukeStub);

            assertEquals(true, uiSideEffects.uiReportUntagTask);

            uiSideEffects.reset();
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
