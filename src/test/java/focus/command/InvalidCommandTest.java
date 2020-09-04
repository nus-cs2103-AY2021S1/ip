package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class InvalidCommandTest {
    private final InvalidCommand invalidCommand = new InvalidCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(invalidCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            invalidCommand.execute("blah", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tOops! I'm not sure what you meant!\n"
                    + "\tPlease try again!", e.getMessage());
        }
    }
}
