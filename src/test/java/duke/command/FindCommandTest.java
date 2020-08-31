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

public class FindCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("borrow book");
        Task task2 = new Deadline("readbook", "12/02/2012 12:12");
        Task task3 = new Event("return book", "12/04/2014 12:14");
        Task task4 = new Todo("eat");
        Task task5 = new Todo("sleep");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
        tasks.addTask(task5);
        Ui ui = new Ui();
        Storage storage = new Storage();
        FindCommand findCommand = new FindCommand("book");
        CommandResponse actual = findCommand.execute(tasks, ui, storage);
        String expectedMessage = "Here are the matching tasks in your list:\n"
                + "\t 1.[T][✘] borrow book\n"
                + "\t 2.[D][✘] readbook (by: 12 February 2012, 12:12 PM)\n"
                + "\t 3.[E][✘] return book (at: 12 April 2014, 12:14 PM) ";
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}


