import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.TaskList;

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
