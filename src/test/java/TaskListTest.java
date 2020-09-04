import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Task;
import duke.TaskList;
import duke.Todo;

public class TaskListTest {
    @Test
    public void testEmptyTaskList() {
        assertEquals("", new TaskList().iterateAndPrintToDo());
    }

    @Test
    public void testFilledTaskList() {
        List<Task> list = new ArrayList<>();
        list.add(new Todo(false, 1, "Wash clothes"));
        assertEquals("1. [T][✗] Wash clothes\n", new TaskList(list).iterateAndPrintToDo());
    }

    @Test
    public void testDeleteTask() {
        List<Task> list = new ArrayList<>();
        list.add(new Todo(false, 1, "Wash clothes"));
        list.add(new Deadline(false, 2, "Do testing", "2020-10-10"));
        TaskList test = new TaskList(list);
        test.deleteTask(1);
        assertEquals("1. [D][✗] Do testing (by: 10 Oct 2020)\n", test.iterateAndPrintToDo());
    }
}
