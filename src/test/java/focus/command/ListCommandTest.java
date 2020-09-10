package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class ListCommandTest {
    private final ListCommand listCommand = new ListCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(listCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            listCommand.execute("blah", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: There are currently no tasks on your list!\n"
                    + "\tStart adding one now!", e.getMessage());
        }
    }
}
