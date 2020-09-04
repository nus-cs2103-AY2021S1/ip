package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest extends CommandTests {
    @Test
    public void test() {
        ExitCommand cmd = new ExitCommand();
        assertTrue(cmd.isExit());
        assertEquals(ui.goodbye(), cmd.execute(taskList, ui, storage));
    }
}
