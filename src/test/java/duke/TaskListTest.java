package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Todo("Test", false));
        TaskList taskList = new TaskList(tasks);
        assertEquals(tasks, taskList.getTasks());
    }

    @Test
    public void getCount() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Todo("Test", false));
        TaskList taskList = new TaskList(tasks);
        assertEquals(1, taskList.getCount());
    }

    @Test
    public void completeTask() {
        List<Task> tasks = new ArrayList<Task>();
        Todo todo = new Todo("Test", false);
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        Todo expectedTodo = new Todo("Test", true);
        assertEquals(expectedTodo.toString(), taskList.completeTask(1).toString());
    }
}
