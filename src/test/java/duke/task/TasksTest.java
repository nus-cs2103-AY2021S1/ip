package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.ReadFailedException;

public class TasksTest {
    @Test
    public void testGetTasks() {
        assertEquals(createTasks().getSize(), createTasks().getTasks().size());
    }

    private Tasks createTasks() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("todo"));
        tasks.addTask(new Deadline("deadline", LocalDate.parse("2020-02-12")));
        tasks.addTask(new Event("event", LocalDate.parse("2020-04-12")));
        return tasks;
    }

    @Test
    public void testGetTask_success() throws IndexOutOfBoundsException {
        assertEquals(new Todo("todo").toString(), createTasks().getTask(0).toString());
    }

    @Test
    public void testGetTask_exceptionThrown() {
        try {
            createTasks().getTask(10);
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("Index 10 out of bounds for length 3", ex.getMessage());
        }
    }

    @Test
    public void testGetSize() {
        assertEquals(3, createTasks().getSize());
    }

    @Test
    public void testGetData() {
        String expected = new Todo("todo").getData() + "\n";
        expected += new Deadline("deadline", LocalDate.parse("2020-02-12")).getData() + "\n";
        expected += new Event("event", LocalDate.parse("2020-04-12")).getData() + "\n";
        assertEquals(expected, createTasks().getData());
    }

    @Test
    public void testAddTask_success() throws ReadFailedException {
        Tasks tasks1 = new Tasks();
        tasks1.addTask(new String[]{"T", "0", "todo"});
        tasks1.addTask(new String[]{"D", "0", "deadline", "2020-02-12"});
        tasks1.addTask(new String[]{"E", "0", "event", "2020-04-12"});
        assertEquals(3, tasks1.getSize());
    }

    @Test
    public void testAddTask_exceptionThrown() {
        try {
            createTasks().addTask(new String[]{"A", "0", "todo"});
            fail(); // the test should not reach this line
        } catch (ReadFailedException ex) {
            assertEquals("Failed to read tasks!", ex.getMessage());
        }
    }

    @Test
    public void testRemoveTask_success() throws IndexOutOfBoundsException {
        Tasks tasks = createTasks();
        tasks.removeTask(0);
        assertEquals(2, tasks.getSize());
    }

    @Test
    public void testRemoveTask_exceptionThrown() {
        try {
            createTasks().removeTask(10);
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("Index 10 out of bounds for length 3", ex.getMessage());
        }
    }

    @Test
    public void testFindByDate() {
        assertEquals(0, createTasks().findByDate(LocalDate.parse("2020-05-20")).size());
        assertEquals(1, createTasks().findByDate(LocalDate.parse("2020-04-12")).size());
    }
}
