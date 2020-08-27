package duke.command;

import duke.DukeStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

            assertEquals(true, dukeStub.state.exitLoop);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
