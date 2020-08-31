import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.Storage;
import duke.Tasklist;
import duke.exception.DukeTaskException;

public class ParserTest {

    private Storage storage = new Storage();
    private Tasklist tasklist = new Tasklist(storage);
    private Parser parser;

    @Test
    public void addInvalidTask() throws IOException {
        tasklist.loadList();
        try {
            parser.commandTasks(tasklist, "todo", "");
        } catch (DukeTaskException e) {
            assertEquals("You might have left your message or duration empty.",
                    e.getMessage());
        }

    }
}
