package duke.command;

import duke.exception.NoDescriptionException;
import duke.DukeStub;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {

    private final TodoCommand command = new TodoCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new TodoCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_success() {
        String normalInput = "read book";

        try {
            command.execute(normalInput, dukeStub);
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
