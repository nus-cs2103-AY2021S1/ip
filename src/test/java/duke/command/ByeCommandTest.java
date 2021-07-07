package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommandTest {
    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        ByeCommand byeCommand = new ByeCommand();
        CommandResponse actual = byeCommand.execute(tasks, ui, storage);
        String expectedMessage = "Bye. Hope to see you again soon!";
        CommandResponse expected = new CommandResponse(expectedMessage, true);
        assertEquals(expected, actual);
    }
}
