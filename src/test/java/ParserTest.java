import duke.*;
import duke.exception.DukeTaskException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Storage storage = new Storage();
    Tasklist tasklist = new Tasklist(storage);
    Parser parser;

    @Test
    public void addInvalidTask() throws IOException{
        tasklist.loadList();
        try {
            parser.commandTasks(tasklist, "todo", "");
        } catch (DukeTaskException e) {
            assertEquals("You might have left your message or duration empty.",
                    e.getMessage());
        }
    }

}