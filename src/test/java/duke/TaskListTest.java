package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetSize() {
        int size = new TaskList(new ArrayList<>()).getSize();
        assertEquals(0, size);
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Task("task"));
        int size = taskList.getSize();

        assertEquals(1, size);
    }

    @Test
    public void testDeleteTask() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Task("task"));
        taskList.deleteTask(1);
        int size = taskList.getSize();

        assertEquals(0, size);
    }
}
