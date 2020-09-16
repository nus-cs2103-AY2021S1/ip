package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

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
        } catch (FocusException e) {
            assertEquals("\tERROR: Please enter a keyword you wish to find!", e.getMessage());
        }
        try {
            findCommand.execute("find ", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please enter a keyword you wish to find!", e.getMessage());
        }
    }
}
