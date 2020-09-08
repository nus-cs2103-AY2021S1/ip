import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final TaskList tasks = new TaskList();

    @Test
    public void createTask_emptyTaskList() {
        String expected = "Got it. I've added this task:\n"
                + " [T][✘] cry\n"
                + "Now you have 1 tasks in the list";

        try {
            Task newTask = new Todo("cry");
            String actual = tasks.createTask(newTask);
            assertEquals(expected, actual);

        } catch (EmptyBodyException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void markAsDone_task() {
        String expected = "Nice! I've marked this task as done:\n"
                + " [T][✓] cry";
        try {
            Task newTask = new Todo("cry");
            tasks.createTask(newTask);
            String actual = tasks.markAsDone(1);
            assertEquals(expected, actual);
        } catch (IndexOutOfBoundsDukeException | EmptyBodyException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void deleteTask_task() {
        String expected = "Okay. I've removed this task:\n"
                + " [T][✘] cry";
        try {
            Task newTask = new Todo("cry");
            tasks.createTask(newTask);
            String actual = tasks.deleteTask(1);
            assertEquals(expected, actual);
        } catch (IndexOutOfBoundsDukeException | EmptyBodyException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void toString_task() {
        String expected = " 1. [T][✘] cry\n";
        try {
            Task newTask = new Todo("cry");
            tasks.createTask(newTask);
            String actual = tasks.toString();
            assertEquals(expected, actual);
        } catch (EmptyBodyException e) {
            fail("Expected: " + expected + "\n Actual:" + e.toString());
        }
    }
}
