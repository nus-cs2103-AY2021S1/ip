package duke.task;

import duke.exception.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Tasks.
 */
public class TasksTest {
    /**
     * Returns a tasks.
     *
     * @return the tasks.
     */
    private Tasks createTasks() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("todo"));
        tasks.addTask(new Deadline("deadline", LocalDate.parse("2020-02-12")));
        tasks.addTask(new Event("event", LocalDate.parse("2020-04-12")));
        return tasks;
    }

    /**
     * Test getTasks method.
     */
    @Test
    public void testGetTasks() {
        assertEquals(createTasks().getSize(), createTasks().getTasks().size());
    }

    /**
     * Test getTask method with success.
     *
     * @throws IndexOutOfBoundsException If the task cannot be retrieved.
     */
    @Test 
    public void testGetTask_success() throws IndexOutOfBoundsException {
        assertEquals(new Todo("todo").toString(), createTasks().getTask(0).toString());
    }

    /**
     * Test getTask method with exception thrown.
     */
    @Test 
    public void testGetTask_exceptionThrown() {
        try {
            createTasks().getTask(10);
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("Index 10 out of bounds for length 3", ex.getMessage());
        }
    }

    /**
     * Test getSize method.
     */
    @Test
    public void testGetSize() {
        assertEquals(3, createTasks().getSize());
    }

    /**
     * Test getData method.
     */
    @Test
    public void testGetData() {
        String expected = new Todo("todo").getData() + "\n";
        expected += new Deadline("deadline", LocalDate.parse("2020-02-12")).getData() + "\n";
        expected += new Event("event", LocalDate.parse("2020-04-12")).getData() + "\n";
        assertEquals(expected, createTasks().getData());
    }

    /**
     * Test addTask method with success.
     *
     * @throws ReadFailedException If the task cannot be read.
     */
    @Test 
    public void testAddTask_success() throws ReadFailedException {
        Tasks tasks1 = new Tasks();
        tasks1.addTask(new String[] {"T", "0", "todo"});
        tasks1.addTask(new String[] {"D", "0", "deadline", "2020-02-12"});
        tasks1.addTask(new String[] {"E", "0", "event", "2020-04-12"});
        assertEquals(3, tasks1.getSize());
    }

    /**
     * Test addTask method with exception thrown.
     */
    @Test
    public void testAddTask_ExceptionThrown() {
        try {
            createTasks().addTask(new String[] {"A", "0", "todo"});
            fail(); // the test should not reach this line
        } catch (ReadFailedException ex) {
            assertEquals("Failed to read tasks!", ex.getMessage());
        }
    }

    /**
     * Test removeTask method with success.
     *
     * @throws IndexOutOfBoundsException the index out of bounds exception.
     */
    @Test 
    public void testRemoveTask_success() throws IndexOutOfBoundsException {
        Tasks tasks = createTasks();
        tasks.removeTask(0);
        assertEquals(2, tasks.getSize());
    }

    /**
     * Test removeTask method with exception thrown.
     */
    @Test
    public void testRemoveTask_ExceptionThrown() {
        try {
            createTasks().removeTask(10);
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("Index 10 out of bounds for length 3", ex.getMessage());
        }        
    }

    /**
     * Test findByDate method.
     */
    @Test     
    public void testFindByDate() {
        assertEquals(0, createTasks().findByDate(LocalDate.parse("2020-05-20")).size());
        assertEquals(1, createTasks().findByDate(LocalDate.parse("2020-04-12")).size());
    }
}
