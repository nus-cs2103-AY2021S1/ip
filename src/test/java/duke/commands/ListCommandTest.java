package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private Command command = new ListCommand();
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList list = new TaskList();

    @Test
    public void noList_exceptionThrown() {
        try {
            command.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, there's nothing on the list yet.");
        }
    }
}
