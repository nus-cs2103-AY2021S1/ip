package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.ui.UiSideEffects;

public class HelpCommandTest {

    private final HelpCommand command = new HelpCommand();

    private final DukeStub dukeStub = new DukeStub();

    private UiSideEffects uiSideEffects = UiSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new HelpCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_anyInput_success() {
        String input = "123";

        try {
            command.execute(input, dukeStub);

            assertEquals(true, uiSideEffects.uiShowAllCommands);

            uiSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
