package duke.command;

import duke.ui.UiSideEffects;
import duke.exception.NoDescriptionException;
import duke.DukeStub;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {

    private final TodoCommand command = new TodoCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

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
        String invalidInput = "";

        Exception exception = assertThrows(NoDescriptionException.class,
                () -> command.execute(invalidInput, dukeStub));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nOOPS!!! The description of a todo cannot be empty.\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }
}
