package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;





public class CommandsTest {
    @Test
    public void testAddCommand() {
        AddCommand addTodo = new AddCommand(AddCommand.Type.TODO, "read book");
        AddCommand addDeadline = new AddCommand(AddCommand.Type.DEADLINE, "read book /by 12-12-2020 23:54");
        AddCommand addEvent = new AddCommand(AddCommand.Type.EVENT, "read book /at 12-12-2020 23:54");

        Storage storage = new Storage();
        TaskList tasks = new TaskList();

        addTodo.execute(tasks, storage);
        addDeadline.execute(tasks, storage);
        addEvent.execute(tasks, storage);
        assertEquals(3, tasks.size());
    }

    @Test
    public void testListCommand() throws DukeException {
        ListCommand list = new ListCommand("list");

        Storage storage = new Storage();
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("mid", 1, "eat"));
        tasks.addTask(new Todo("low", 1, "eat"));
        tasks.addTask(new Todo("high", 1, "eat"));
        tasks.addTask(new Todo("mid", 1, "eat"));

        list.execute(tasks, storage);
        Task.Priority priorityFirst = tasks.getTask(1).getPriority();
        Task.Priority priorityLast = tasks.getTask(4).getPriority();

        assertEquals(Task.Priority.HIGH, priorityFirst);
        assertEquals(Task.Priority.LOW, priorityLast);
    }
}
