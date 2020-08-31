package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommandTest {

    @Test
    public void testExecute() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand("eat", "22/02/1998 12:30");
        CommandResponse actual = addDeadlineCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it. I've added this deadline: \n\t   "
                + "[D][✘] eat (by: 22 February 1998, 12:30 PM)\n\t "
                + "Now you have 1 task in the list.";
        assertEquals("[D][✘] eat (by: 22 February 1998, 12:30 PM)", tasks.getTask(0).toString());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}
