package duke.tasklist;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test() {
        List<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        assertTrue(taskList.isEmpty());

        Task newTask = new ToDo("test1");

        taskList.add(newTask);
        taskList.add(new ToDo("test2"));
        taskList.add(new ToDo("test3"));
        taskList.add(new ToDo("test4"));
        taskList.add(new ToDo("test5"));

        assertFalse(taskList.isEmpty());
        assertEquals(newTask, taskList.get(0));
        assertTrue(taskList.checkIfValid(5));
        assertFalse(taskList.checkIfValid(10));
    }
}
