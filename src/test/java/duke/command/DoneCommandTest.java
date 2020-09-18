package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommandTest {

    @Test
    public void testExecute() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Task task = new Todo("eat");
        tasks.addTask(task);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DoneCommand doneCommand = new DoneCommand(0);
        CommandResponse actual = doneCommand.execute(tasks, ui, storage);
        String expectedMessage = "Nice! I've marked this task as done:\n\t   "
                + "[T][✓] eat";
        assertEquals(1, tasks.getNumberOfTask());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}

