package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for exit command.
 */
public class ExitCommandTest extends CommandTests {

    /**
     * Basic tests.
     */
    @Test
    public void execute_() {
        ExitCommand cmd = new ExitCommand();
        // Tests
        assertTrue(cmd.isExit());
        assertEquals(ui.goodbye(), cmd.execute(taskList, ui, storage));
    }
}
