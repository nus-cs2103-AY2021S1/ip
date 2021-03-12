import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import task.Task;
import task.Todo;

public class TaskListTest {

    @Test
    public void addTask_todo_sizeOne() {
        Task todo = new Todo("Eat Breakfast");
        TaskList tasks = new TaskList();

        tasks.add(todo);
        assertEquals(1, tasks.size());
    }

    @Test
    public void getTask_todo_todoEqualToString() {
        Task todo = new Todo("Do CS2103T iP");
        TaskList tasks = new TaskList();

        tasks.add(todo);
        assertEquals(tasks.get(0).toString(), todo.toString());
    }

    @Test
    public void removeTask_todo_sizeZero() {
        Task todo = new Todo("Eat Dinner");
        TaskList tasks = new TaskList();

        tasks.add(todo);
        assertEquals(tasks.size(), 1);
        tasks.remove(0);
        assertEquals(tasks.size(), 0);
    }
}
