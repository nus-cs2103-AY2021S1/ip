package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void newTask_validInput_taskObjectReturned() {
        try {
            Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", false);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void setDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", false);
        task.setDone(true);
        assertEquals("[E]|1|meeting|NUS", task.toStringForMemory());
    }

    @Test
    public void toStringForMemory_taskNotDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", false);
        assertEquals("[E]|0|meeting|NUS", task.toStringForMemory());
    }

    @Test
    public void toStringForMemory_taskDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", true);
        assertEquals("[E]|1|meeting|NUS", task.toStringForMemory());
    }

    @Test
    public void toStringForGui_taskNotDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", false);
        assertEquals("[E][\u2718] meeting (at: NUS)", task.toStringForGui());
    }

    @Test
    public void toStringForGui_taskDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", true);
        assertEquals("[E][\u2713] meeting (at: NUS)", task.toStringForGui());
    }

    @Test
    public void toStringForCli_taskNotDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", false);
        assertEquals("[E][✘] meeting (at: NUS)", task.toStringForCli());
    }

    @Test
    public void toStringForCli_taskDone() {
        Task task = new Task("meeting", " (at: NUS)", "|NUS", "[E]", "event", true);
        assertEquals("[E][✓] meeting (at: NUS)", task.toStringForCli());
    }
}
