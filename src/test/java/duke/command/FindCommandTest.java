package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {
    private final FindCommand findCommand = new FindCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(findCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            findCommand.execute("find", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease enter a keyword you wish to find!", e.getMessage());
        }
        try {
            findCommand.execute("find ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease enter a keyword you wish to find!", e.getMessage());
        }
    }
}
