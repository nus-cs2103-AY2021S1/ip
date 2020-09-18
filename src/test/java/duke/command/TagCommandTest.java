package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.exception.BlankTagException;
import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidIndexException;
import duke.ui.UiSideEffects;

public class TagCommandTest {

    private final TagCommand command = new TagCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new TagCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_success() {
        int index = 1;
        String tag = "hahaha";
        String input = index + " " + tag;

        try {
            command.execute(input, dukeStub);

            String taskTag = dukeStub.getTaskList().getTask(1).getTag();

            assertEquals(tag, taskTag);
            assertEquals(true, uiSideEffects.uiReportTagTask);

            uiSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_incorrectFormatInput_exceptionThrown() {
        String input = "asdf";

        Exception exception = assertThrows(
                IncorrectFormatException.class, () -> command.execute(input, dukeStub));

        String errMessage = ExceptionMessage.TAG_INCORRECT_FORMAT_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void execute_invalidIndexInput_exceptionThrown() {
        String invalidIndex = "one";
        String input = invalidIndex + " hahaha";

        Exception exception = assertThrows(
                InvalidIndexException.class, () -> command.execute(input, dukeStub));

        String errMessage = ExceptionMessage.getInvalidIndexMessage(invalidIndex);

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void execute_blankTagInput_exceptionThrown() {
        String input = "1      ";

        Exception exception = assertThrows(
                BlankTagException.class, () -> command.execute(input, dukeStub));

        String errMessage = ExceptionMessage.BLANK_TAG_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }
}
