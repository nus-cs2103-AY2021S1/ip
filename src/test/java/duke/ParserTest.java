package duke;

import duke.command.*;
import duke.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void byeTest() throws DukeException {
        Command c = Parser.parse("bye");
        assertEquals(c, new ByeCommand("bye", true));
    }

    @Test
    public void listTest() throws DukeException {
        Command c = Parser.parse("list");
        assertEquals(c, new ListCommand("list"));
    }

    @Test
    public void doneTest() throws DukeException {
        Command c = Parser.parse("done 1");
        assertEquals(c, new DoneCommand("done", "1"));
    }

    @Test
    public void deleteTest() throws DukeException {
        Command c = Parser.parse("delete 1");
        assertEquals(c, new DeleteCommand("delete", "1"));
    }

    @Test
    public void addTest() throws DukeException {
        Command c = Parser.parse("todo borrow book");
        assertEquals(c, new AddCommand("todo", "borrow book"));
    }

    @Test
    public void unknownTest() throws DukeException {
        Command c = Parser.parse("unknown");
        assertEquals(c, new UnknownCommand("unknown"));
    }
}
