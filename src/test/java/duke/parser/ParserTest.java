package duke.parser;

import duke.command.ByeCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_validCommand_success() throws DukeException {
        TaskList tasks = new TaskList();
        assertEquals(new ByeCommand().toString(),Parser.parse("bye", tasks).toString());
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            assertEquals(new ByeCommand().toString(), Parser.parse("hello", tasks).getClass());
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
