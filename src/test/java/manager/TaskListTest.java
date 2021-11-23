package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.InvalidDescriptionException;
import exceptions.InvalidTimeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskListTest {

    @Test
    public void testGetNumberOfTasks() {
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("get schwifty"));
            taskList.addTask(new Event("say goodbye to moonmen", "5000-12-30"));
            taskList.addTask(new Deadline("not get a dragon", "forever"));
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(3, taskList.getNumberOfTasks());
    }

    @Test
    public void testGetList() {
        assertTrue(new TaskList().getList() instanceof ArrayList);
    }
}
