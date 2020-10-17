package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class ByeCommandTest extends CommandTest {
    /**
     * Tests if the Bye Command outputs the correct message.
     */
    @Test
    public void execute_validByeCommand_correctOutput() {
        try {
            ByeCommand byeCommand = new ByeCommand();
            String output = byeCommand.execute();
            assertEquals(Ui.bye(), output);
        } catch (Exception e) {
            fail();
        }
    }
}
