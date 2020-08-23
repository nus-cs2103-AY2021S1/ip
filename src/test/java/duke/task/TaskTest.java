package duke.task;

import duke.exception.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    private Task createTodo() {
        return new Todo("todo");
    }

    private Task createEvent() {
        return new Event("event", LocalDate.parse("2020-03-23"));
    }

    private Task createDeadline() {
        return new Deadline("deadline", LocalDate.parse("2020-03-07"));
    }

    @Test
    public void testHasDate() {
        assertFalse(createTodo().hasDate());
        assertTrue(createEvent().hasDate());
        assertTrue(createDeadline().hasDate());
    }

    @Test 
    public void testMarkAsDone() {
        Task task = createTodo();
        task.markAsDone();
        assertEquals("[T][\u2713] todo", task.toString());
    }

    @Test 
    public void testCreateTask_success() throws ReadFailedException {
        assertEquals(createTodo().toString(), 
                Task.createTask(new String[] {"T", "0", "todo"}).toString());
        assertEquals(createEvent().toString(), 
                Task.createTask(new String[] {"E", "0", "event", "2020-03-23"}).toString());
        assertEquals(createDeadline().toString(), 
                Task.createTask(new String[] {"D", "0", "deadline", "2020-03-07"}).toString());
        
    }

    @Test
    public void testCreateTask_ExceptionThrown() {
        try {
            Task.createTask(new String[] {"J", "0", "todo"});
            fail(); // the test should not reach this line
        } catch (ReadFailedException ex) {
            assertEquals("Failed to read tasks!", ex.getMessage());
        }
    }

    @Test 
    public void testGetStatusIcon() {
        assertEquals("\u2718", createTodo().getStatusIcon());
//        Task task = createTodo();
//        task.markAsDone();
//        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void testGetData() {
        assertEquals("T_0_todo", createTodo().getData());
    }


    @Test
    public void testToString() {
        assertEquals("[T][\u2713] todo", createTodo().toString());
    }
}
