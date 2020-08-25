import duke.*;
import duke.command.ListCommand;
import duke.exception.DukeListException;
import duke.exception.DukeTaskException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    Storage storage = new Storage();
    Tasklist tasklist = new Tasklist(storage);

    @Test
    public void invalidListCommand() throws IOException{
        tasklist.loadList();
        try {
            new ListCommand().execute(tasklist, new UserInterface());
        } catch (DukeListException e) {
            assertEquals("Your list is empty.",
                    e.getMessage());
        }
    }

}