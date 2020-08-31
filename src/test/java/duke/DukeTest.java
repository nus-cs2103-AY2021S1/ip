package duke;

import duke.command.*;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
