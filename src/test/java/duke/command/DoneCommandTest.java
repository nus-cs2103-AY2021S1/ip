package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
        } catch (DukeException e) {
            assertEquals("Please enter a task number you wish to mark done!\n"
                    + "You have " + taskList.getSize() + " tasks on your list now.", e.getMessage());
        }
        try {
            doneCommand.execute("done 0", taskList, storage);
        } catch (DukeException e) {
            assertEquals("There is no such task number.\n"
                    + "Please enter a valid one!", e.getMessage());
        }
        try {
            doneCommand.execute("done 5", taskList, storage);
        } catch (DukeException e) {
            assertEquals("There is no such task number.\n"
                    + "Please enter a valid one!", e.getMessage());
        }
    }
}
