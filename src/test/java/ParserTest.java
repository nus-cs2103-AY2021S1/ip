import duke.Parser;
import duke.TaskList;
import exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void processMsg_invalidCommand_exceptionThrown() {
        try {
            Parser p = new Parser(new TaskList());
            p.processMsg("test");
        } catch (Exception e) {
            assertEquals("Specified action is not recognised.", e.getMessage());
        }
    }
}
