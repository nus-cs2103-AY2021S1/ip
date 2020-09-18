import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private Task task = new Task("return book");
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    /**
     * Tests the getDescription() method from the Task Class
     */
    @Test
    public void testGetDescription() {
        assertEquals("return book", task.getDescription());
    }

    /**
     * Tests the isDone() method from the Task Class
     */
    @Test
    public void testIsDone() {
        assertFalse(task.isDone());
    }

    /**
     * Tests the setDone() method from the Task Class
     */
    @Test
    public void testSetDone() {
        task.setDone();
        assertTrue(task.isDone);
    }
    /**
     * Tests the setUndone() method from the Task Class
     */
    @Test
    public void testSetUndone() {
        task.setUndone();
        assertFalse(task.isDone);
    }

    /**
     * Tests the getStatusIcon() method from the Task Class
     */
    @Test
    public void testGetStatusIcon() {
        task.setDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    /**
     * Tests the getString() method from the Todo Class
     */
    @Test
    public void testTodoGetString() {
        Todo todo = new Todo("return book");
        assertEquals(todo.toString(), "[T][" + todo.getStatusIcon() + "] " + todo.getDescription());
    }

    /**
     * Tests the getString() method from the Deadline class
     */
    @Test
    public void testDeadlineGetString() {
        Deadline deadline = new Deadline("homework", "idk");
        assertEquals(deadline.toString(), "[D][" + deadline.getStatusIcon() + "] "
                + deadline.getDescription() + " (by:" + "idk)");
    }
    /**
     * Tests the getString method from the Events class
     */
    @Test
    public void testEventsGetString() {
        Events event = new Events("party", "3pm");
        assertEquals(event.toString(), "[E][" + event.getStatusIcon() + "] "
                + event.getDescription() + " (at:" + "3pm)");
    }
    /**
     * Tests the getString method from the DukeException class
     */
    @Test
    public void testDukeExceptionGetString() {
        DukeException e = new DukeException("");
        assertEquals(e.getMessage(), "Please enter your command.");
    }
}
