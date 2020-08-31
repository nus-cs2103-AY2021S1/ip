package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new Todo("eat");
        tasks.addTask(task);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DeleteCommand deleteCommand = new DeleteCommand(0);
        CommandResponse actual = deleteCommand.execute(tasks, ui, storage);
        String expectedMessage = "Noted. I've removed this task:\n"
                + "\t   [T][✘] eat\n"
                + "\t Now you have 0 task in the list.";
        assertEquals(0, tasks.getNumberOfTask());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}

