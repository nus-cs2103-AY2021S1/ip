package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

public class DoneCommandTest {
    private final DoneCommand doneCommand = new DoneCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(doneCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            doneCommand.execute("done", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.", e.getMessage());
        }
        try {
            doneCommand.execute("done 0", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!", e.getMessage());
        }
        try {
            doneCommand.execute("done 5", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!", e.getMessage());
        }
    }
}
