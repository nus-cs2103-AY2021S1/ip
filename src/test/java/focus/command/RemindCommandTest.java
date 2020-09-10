package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class RemindCommandTest {
    private final RemindCommand remindCommand = new RemindCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(remindCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            remindCommand.execute("blah", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: There are currently no tasks on your list!\n"
                    + "\tStart adding one now!", e.getMessage());
        }
    }
}
