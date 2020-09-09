import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.UserInterface;
import duke.command.ListCommand;
import duke.exception.DukeListException;
import duke.task.TaskList;

public class ListCommandTest {

    private Storage storage = new Storage();
    private TaskList tasklist = new TaskList(storage);

    @Test
    public void invalidListCommand() throws IOException {
        tasklist.loadList();
        try {
            new ListCommand().execute(tasklist, new UserInterface());
        } catch (DukeListException e) {
            assertEquals("Your list is empty.", e.getMessage());
        }
    }

}
