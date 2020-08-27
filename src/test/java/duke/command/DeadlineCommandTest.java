package duke.command;

import duke.ui.UiSideEffects;
import duke.exception.IncorrectFormatException;
import duke.DukeStub;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineCommandTest {

    private final DeadlineCommand command = new DeadlineCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new DeadlineCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskAdded() {
        String normalInput = "return book /by 12:33:01";
        int currentTaskListSize = dukeStub.taskList.tasks.size();

        try {
            command.execute(normalInput, dukeStub);

            assertEquals(currentTaskListSize + 1, dukeStub.taskList.tasks.size());
            assertEquals(true, uiSideEffects.uiReportNewTask);

            uiSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        String invalidInput = "return book";

        Exception exception = assertThrows(IncorrectFormatException.class,
                () -> command.execute(invalidInput, dukeStub));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nPlease follow the format of deadline <duke.task description> /by <deadline>\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }
}
