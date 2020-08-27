import command.*;
import exception.DukeException;
import org.junit.Test;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import tasklist.TaskList;
import ui.Ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DukeTest {
    @Test
    public void testCommands() throws DukeException {
        assertTrue(Parser.parse("Wrong Command") instanceof InvalidCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("filter 20/03/2020") instanceof FilterCommand);
        assertTrue(Parser.parse("todo t1") instanceof TodoCommand);
        assertTrue(Parser.parse("deadline d1 /by 20/02/2020 1200") instanceof DeadlineCommand);
        assertTrue(Parser.parse("event e1 /at 20/02/2020 1200") instanceof EventCommand);
    }

    @Test(expected = DukeException.class)
    public void testIncorrectDone() throws DukeException {
        Parser.parse("done").execute(new TaskList(), new Ui(), new Storage("demo"));
    }

    @Test(expected = DukeException.class)
    public void testIncorrectDelete() throws DukeException {
        Parser.parse("delete").execute(new TaskList(), new Ui(), new Storage("demo"));
    }

    @Test(expected = DukeException.class)
    public void testIncorrectTodo() throws DukeException {
        Parser.parse("todo").execute(new TaskList(), new Ui(), new Storage("demo"));
    }

    @Test(expected = DukeException.class)
    public void testIncorrectDeadline() throws DukeException {
        Parser.parse("deadline d1 ").execute(new TaskList(), new Ui(), new Storage("demo"));
    }

    @Test(expected = DukeException.class)
    public void testIncorrectEvent() throws DukeException {
        Parser.parse("event e1").execute(new TaskList(), new Ui(), new Storage("demo"));
    }


    @Test
    public void testExits() {
        assertEquals(new ByeCommand().isExit(), true);
        assertEquals(new ListCommand().isExit(), false);
        assertEquals(new InvalidCommand().isExit(), false);
        assertEquals(new DeadlineCommand("test").isExit(), false);
        assertEquals(new EventCommand("test").isExit(), false);
        assertEquals(new TodoCommand("test").isExit(), false);
        assertEquals(new DoneCommand("test").isExit(), false);
        assertEquals(new FilterCommand("test").isExit(), false);
        assertEquals(new DeleteCommand("test").isExit(), false);
    }
}
