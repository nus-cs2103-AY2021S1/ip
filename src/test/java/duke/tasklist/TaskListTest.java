package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {

    @Test
    public void testToString() {
        TaskList tasks = new TaskList();
        Task todo = new Todo("Task 1");
        tasks.getList().add(todo);

        String expected = "Here are the tasks in your list:\n\t "
                + "1.[T][✘] Task 1 ";
        assertEquals(expected, tasks.toString());
    }

    @Test
    public void testAddTask() {
        TaskList tasks1 = new TaskList();
        TaskList tasks2 = new TaskList();
        Task todo = new Todo("Task 1");
        Task deadline = new Deadline("Task 2", "12/02/2020 12:30");
        tasks1.addTask(todo);
        tasks2.addTask(todo);
        tasks2.addTask(deadline);
        assertEquals(1, tasks1.getList().size());
        assertEquals(2, tasks2.getList().size());
    }

    @Test
    public void testRemoveTask() throws InvalidTaskNumberException {
        TaskList tasks1 = new TaskList();
        Task todo = new Todo("Task 1");
        Task deadline = new Deadline("Task 2", "12/02/2020 12:30");
        tasks1.addTask(todo);
        tasks1.addTask(deadline);
        assertEquals(2, tasks1.getList().size());
        tasks1.removeTask(1);
        assertEquals(1, tasks1.getList().size());
    }

    @Test
    public void testGetTask() throws InvalidTaskNumberException {
        TaskList tasks1 = new TaskList();
        Task todo = new Todo("Task 1");
        tasks1.addTask(todo);
        assertEquals("[T][✘] Task 1", tasks1.getTask(0).toString());
    }

    @Test
    public void testGetNumberOfTask() {
        TaskList tasks1 = new TaskList();
        TaskList tasks2 = new TaskList();
        Task todo = new Todo("Task 1");
        Task deadline = new Deadline("Task 2", "12/02/2020 12:30");
        tasks1.addTask(todo);
        tasks2.addTask(todo);
        tasks2.addTask(deadline);
        assertEquals(1, tasks1.getNumberOfTask());
        assertEquals(2, tasks2.getNumberOfTask());
    }

}
