package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

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

    @Test
    public void findTest() throws DukeException {
        Command c = Parser.parse("find book");
        assertEquals(c, new FindCommand("find", "book"));
    }
}
