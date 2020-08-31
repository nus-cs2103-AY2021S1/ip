package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("eat");
        Task task2 = new Deadline("sleep", "12/02/2012 12:12");
        Task task3 = new Event("play", "12/04/2014 12:14");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        Ui ui = new Ui();
        Storage storage = new Storage();
        ListCommand listCommand = new ListCommand();
        CommandResponse actual = listCommand.execute(tasks, ui, storage);
        String expectedMessage = "Here are the tasks in your list:\n"
                + "\t 1.[T][✘] eat\n"
                + "\t 2.[D][✘] sleep (by: 12 February 2012, 12:12 PM)\n"
                + "\t 3.[E][✘] play (at: 12 April 2014, 12:14 PM) ";
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}
