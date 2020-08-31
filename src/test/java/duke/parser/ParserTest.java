package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class ParserTest {

    @Test
    public void parse_validCommand_success() throws DukeException {
        TaskList tasks = new TaskList();
        assertEquals(new ByeCommand(), Parser.parse("bye", tasks));
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            assertEquals(new ByeCommand(), Parser.parse("hello", tasks));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
