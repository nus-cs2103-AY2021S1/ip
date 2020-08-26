import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskListTest {
    
    @Test
    public void addThreeTasksTest() {
        //set up
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task a = new ToDo("a");
            Task b = new ToDo("b");
            Task c= new ToDo("c");
            
            //add
            tasks.addTask(a);
            tasks.addTask(b);
            tasks.addTask(c);
        } catch (PandaBotEmptyTaskDescriptionException e) {
            System.out.println(e.getMessage());
        }

        //test
        assertEquals(3, tasks.size());
    }
    
    @Test
    public void deleteTwoTasksThenAddOneSuccessfullyTest() {
        //set up
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task a = new ToDo("a");
            Task b = new ToDo("b");
            Task c= new ToDo("c");
            tasks.addTask(a);
            tasks.addTask(b);
            tasks.addTask(c);
            
            //delete and add
            tasks.deleteTask(0);
            tasks.deleteTask(1);
            tasks.addTask(new ToDo("d"));
        } catch (PandaBotEmptyTaskDescriptionException | PandaBotOutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        //test
        assertEquals(2, tasks.size());
    }

    @Test
    public void deleteTaskOutOfRangeTest() {
        //set up
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task a = new ToDo("a");
            Task b = new ToDo("b");
            tasks.addTask(a);
            tasks.addTask(b);
            
            //delete
            tasks.deleteTask(4);
        } catch (PandaBotEmptyTaskDescriptionException | PandaBotOutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        //test
        assertNotEquals(1, tasks.size());
    }
}
