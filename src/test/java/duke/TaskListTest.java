package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;


public class TaskListTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    public void testAddTask() {
        try {
            tasks.addTask("todo project");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("[T][\u2718] project", tasks.getTasks().get(0).toString());
    }

    @Test
    public void testCompleteTask() {
        try {
            tasks.addTask("todo project");
            tasks.completeTask("done 1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("\u2713", tasks.getTasks().get(0).getStatusIcon());
    }

    @Test
    public void testCompleteMultipleTasks() {
        try {
            tasks.addTask("todo 1");
            tasks.addTask("todo 2");
            tasks.completeTask("done todo");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(tasks.getTasks().get(0).getStatusIcon().equals("\u2713")
                && tasks.getTasks().get(1).getStatusIcon().equals("\u2713"));
    }

    @Test
    public void completeTask_exceptionThrown() {
        try {
            tasks.addTask("todo project");
            tasks.completeTask("done 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Please select a task from 1 to 1 for completion.", e.getMessage());
        }

    }

    @Test
    public void testDeleteTask() {
        try {
            tasks.addTask("todo project");
            tasks.deleteTask("delete 1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void testDeleteMultipleTasks() {
        try {
            tasks.addTask("todo 1");
            tasks.addTask("todo 2");
            tasks.deleteTask("delete todo");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void deleteTask_exceptionThrown() {
        try {
            tasks.addTask("todo project");
            tasks.deleteTask("delete 2");
        } catch (DukeException e) {
            assertEquals("Please select a task from 1 to 1 for deletion.", e.getMessage());
        }
    }

    @Test
    public void findTask_exceptionThrown() {
        try {
            tasks.addTask("todo project");
            tasks.find("find");
        } catch (DukeException e) {
            assertEquals("Please key in a keyword to find", e.getMessage());
        }
    }



}
