package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;

public class ExitCommandTest {

    private final ExitCommand command = new ExitCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new ExitCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_anyInput_success() {
        String input = "abc";

        try {
            command.execute(input, dukeStub);

            assertEquals(true, dukeStub.getState().getExitLoop());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
