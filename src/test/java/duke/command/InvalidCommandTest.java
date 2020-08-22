package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        } catch (DukeException e) {
            assertEquals("\tOops! I'm not sure what you meant!\n"
                    + "\tPlease try again!", e.getMessage());
        }
    }
}
