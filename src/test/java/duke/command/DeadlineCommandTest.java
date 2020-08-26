package duke.command;

import duke.exception.IncorrectFormatException;
import duke.DukeStub;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineCommandTest {

    private final DeadlineCommand command = new DeadlineCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new DeadlineCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_success() {
        String normalInput = "return book /by 12:33:01";

        try {
            command.execute(normalInput, dukeStub);
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
