package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class HelpCommandTest {
    private final HelpCommand helpCommand = new HelpCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(helpCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            helpCommand.execute("help me", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Hmm, did you meant the command 'help'?", e.getMessage());
        }
    }
}
