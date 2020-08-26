package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {
    private final Storage storage = new Storage("data", "data/dukeTest.txt");
    private TaskList taskList;
    private final Ui ui = new Ui();

    {
        try {
            taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeTest() {
        DeleteCommand deleteCommand = new DeleteCommand(7);
        try {
            deleteCommand.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Missing or invalid item number!", e.getMessage());
        }
    }

    @Test
    public void isExitTest() {
        assertFalse(new DeleteCommand(10).isExit());
    }
}
