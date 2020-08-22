package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
            assertEquals("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!", e.getMessage());
        }
    }
}
