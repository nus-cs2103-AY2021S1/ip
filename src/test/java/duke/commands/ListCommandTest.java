package duke.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;

public class ListCommandTest {
    private Command command = new ListCommand();
    private Storage storage = new Storage();
    private TaskList list = new TaskList();

    @Test
    public void noList_exceptionThrown() {
        try {
            command.execute(list, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, there's nothing on the list yet.");
        }
    }
}
