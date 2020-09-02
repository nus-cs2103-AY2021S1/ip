package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.WrongFormatException;

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
            assertEquals("\u2718", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatusIcon_taskDone_getTick() {
        try {
            Task task = new Task("do homework", "[T]", "todo", true);
            assertEquals("\u2713", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testSetDone() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            task.setDone(true);
            assertEquals("\u2713", task.getStatusIcon());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testToStringForMemory() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T]|0|do homework", task.toStringForMemory());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testToString() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T][\u2718] do homework", task.toString());
        } catch (WrongFormatException e) {
            fail();
        }
    }
}
