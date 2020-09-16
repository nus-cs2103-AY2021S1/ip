package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEmptyDescriptionException;

public class ParserTest {

    @Test
    public void byeTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("bye");
        assertEquals(c, new ByeCommand("bye"));
    }

    @Test
    public void listTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("list");
        assertEquals(c, new ListCommand("list"));
    }

    @Test
    public void doneTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("done 1");
        assertEquals(c, new DoneCommand("done", "1"));
    }

    @Test
    public void deleteTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("delete 1");
        assertEquals(c, new DeleteCommand("delete", "1"));
    }

    @Test
    public void addTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("todo borrow book");
        assertEquals(c, new AddCommand("todo", "borrow book"));
    }

    @Test
    public void unknownTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("unknown");
        assertEquals(c, new UnknownCommand("unknown"));
    }

    @Test
    public void findTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("find book");
        assertEquals(c, new FindCommand("find", "book"));
    }

    @Test
    public void helpTest() throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        Command c = Parser.parse("help");
        assertEquals(c, new HelpCommand("help"));
    }
}
