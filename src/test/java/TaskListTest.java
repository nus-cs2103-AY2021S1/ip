import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pandabot.exceptions.PandaBotEmptyTaskDescriptionException;
import pandabot.exceptions.PandaBotOutOfRangeException;
import pandabot.tasks.Task;
import pandabot.tasks.TaskList;
import pandabot.tasks.ToDo;

public class TaskListTest {

    @Test
    public void addTask_addThreeTasks_successful() {
        //set up
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task a = new ToDo("a");
            Task b = new ToDo("b");
            Task c = new ToDo("c");

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
    public void deleteAndAddTask_deleteTwoTasksThenAddOneTask_successful() {
        //set up
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task a = new ToDo("a");
            Task b = new ToDo("b");
            Task c = new ToDo("c");
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
    public void deleteTask_taskOutOfRange_exceptionThrownAndNotDeleted() {
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
