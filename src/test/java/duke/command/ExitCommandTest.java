package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

/**
 * Tests for exit command.
 */
public class ExitCommandTest extends CommandTests {

    /**
     * Basic tests.
     */
    @Test
    public void isExit_false_success() {
        try {
            ExitCommand cmd = new ExitCommand();
            // Tests
            assertTrue(cmd.isExit());
            assertEquals(ui.goodbye(), executeTask(cmd));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
