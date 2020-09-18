package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTaskCommandTest {

    @Test
    public void testExecute_todo() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTaskCommand addTaskCommand = new AddTaskCommand(new Todo("eat"));
        CommandResponse actual = addTaskCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it. I've added this task: \n\t   "
                + "[T][✘] eat\n "
                + "Now you have 1 task in the list.";
        assertEquals("[T][✘] eat", tasks.getTask(0).toString());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }

    @Test
    public void testExecute_addDeadline() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTaskCommand addTaskCommand = new AddTaskCommand(new Deadline("eat", "22/02/1998 12:30"));
        CommandResponse actual = addTaskCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it. I've added this task: \n\t   "
                + "[D][✘] eat (by: 22 February 1998, 12:30 PM)\n "
                + "Now you have 1 task in the list.";
        assertEquals("[D][✘] eat (by: 22 February 1998, 12:30 PM)", tasks.getTask(0).toString());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected.getResponseMessage(), actual.getResponseMessage());
    }

    @Test
    public void testExecute_addEvent() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTaskCommand addTaskCommand = new AddTaskCommand(new Event("eat", "22/02/1998 12:30"));
        CommandResponse actual = addTaskCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it. I've added this task: \n\t   "
                + "[E][✘] eat (at: 22 February 1998, 12:30 PM)\n "
                + "Now you have 1 task in the list.";
        assertEquals("[E][✘] eat (at: 22 February 1998, 12:30 PM)", tasks.getTask(0).toString());
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}
