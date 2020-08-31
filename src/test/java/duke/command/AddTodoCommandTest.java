package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTodoCommandTest {

    @Test
    public void testExecute() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTodoCommand addTodoCommand = new AddTodoCommand("eat");
        CommandResponse actual = addTodoCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it. I've added this todo: \n\t   "
                + "[T][✘] eat\n\t "
                + "Now you have 1 task in the list.";
        assertEquals("[T][✘] eat", tasks.getTask(0).toString());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}
