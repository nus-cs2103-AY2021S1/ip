package duke;

import duke.Parser;
import duke.command.AddTaskCommand;
import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseDateTime() {
        assertEquals("2020-04-27T10:00", Parser.parseDateTime("2020-04-27 1000").toString());
    }

    @Test
    void parse_todoCommand_success() throws DukeException {
        assertEquals(new AddTaskCommand(new Todo("hello")), Parser.parse("todo hello"));
    }
}