package duke;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    public void testAddTask(){
        try {
            tasks.addTask("todo project");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals( "[T][✘] project", tasks.getTasks().get(0).toString());
    }

    @Test
    public void testCompleteTask(){
        try {
            tasks.addTask("todo project");
            tasks.completeTask("done 1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("✓", tasks.getTasks().get(0).getStatusIcon());
    }

    @Test
    public void completeTask_exceptionThrown(){
        try {
            tasks.addTask("todo project");
            tasks.completeTask("done 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Please select a task from 1 to 1.", e.getMessage());
        }

    }

    @Test
    public void testDeleteTask(){
        try {
            tasks.addTask("todo project");
            tasks.deleteTask("delete 1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void deleteTask_exceptionThrown(){
        try {
            tasks.addTask("todo project");
            tasks.deleteTask("delete 2");
        } catch (DukeException e) {
            assertEquals("Please select a task from 1 to 1.", e.getMessage());
        }
    }



}
