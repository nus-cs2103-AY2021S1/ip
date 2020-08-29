package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
        } catch (DukeException e) {
            assertEquals("There are currently no tasks on your list!\n"
                    + "Start adding one now!", e.getMessage());
        }
    }
}
