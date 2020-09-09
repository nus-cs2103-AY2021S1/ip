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
    public void getStatusIconForGui_taskNotDone_getCross() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("\u2718", task.getStatusIconForGui());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatusIconForGui_taskDone_getTick() {
        try {
            Task task = new Task("do homework", "[T]", "todo", true);
            assertEquals("\u2713", task.getStatusIconForGui());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatusIconForCli_taskNotDone_getCross() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("✘", task.getStatusIconForCli());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatusIconForCli_taskDone_getTick() {
        try {
            Task task = new Task("do homework", "[T]", "todo", true);
            assertEquals("✓", task.getStatusIconForCli());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testSetDone() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            task.setDone(true);
            assertEquals("\u2713", task.getStatusIconForGui());
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
    public void testToStringForGui() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T][\u2718] do homework", task.toStringForGui());
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void testToStringForCli() {
        try {
            Task task = new Task("do homework", "[T]", "todo", false);
            assertEquals("[T][✘] do homework", task.toStringForCli());
        } catch (WrongFormatException e) {
            fail();
        }
    }
}
