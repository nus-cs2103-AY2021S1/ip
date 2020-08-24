package duke.task;

import duke.exception.WrongFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void newTask_validInput_taskObject() {
        try {
            new Task("do homework", "[T]", "todo", false);
            assertTrue(true);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void newTask_invalidInputNoDescription_taskObject() {
        try {
            new Task("", "[T]", "todo", false);
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getStatusIcon_taskNotDone_getCross() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("✘", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatusIcon_taskDone_getTick() {
        try {
            Task task = new Task("do homework", "[T]", "todo", true);
            assertEquals("✓", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testMarkAsDone() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            task.markAsDone();
            assertEquals("✓", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testStringToSaveInMemory() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T]|0|do homework", task.stringToSaveInMemory());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testToString() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T][✘] do homework", task.toString());
        } catch (WrongFormatException e) {
            fail();
        }
    }
}
