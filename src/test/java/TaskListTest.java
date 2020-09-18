import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTodo_emptyName_exceptionThrown(){
        TaskList task = new TaskList();
        try {
            task.addTask(Todo.createTodo(""));
            fail();
        } catch (DukeException ex) {
            assertEquals("The name of a todo task cannot be empty.", ex.getMessage());
        }
    }

    @Test
    public void addEvent_emptyName_exceptionThrown(){
        TaskList task = new TaskList();
        try {
            task.addTask(Event.createEvent("", "2020-06-04"));
            fail();
        } catch (DukeException ex) {
            assertEquals("The name of an event task cannot be empty.", ex.getMessage());
        }
    }

    @Test
    public void addDeadline_invalidDateFormat_exceptionThrown(){
        TaskList task = new TaskList();
        try {
            task.addTask(Deadline.createDeadline("Deadline 1", "05 May 2020"));
            fail();
        } catch (DukeException ex) {
            assertEquals("Please specify the due date as follows: yyyy-mm-dd", ex.getMessage());
        }
    }
}
