package sg.christopher.duke.entities;

import org.junit.jupiter.api.Test;
import sg.christopher.duke.entities.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void task_shouldBeCreatable() {
        @SuppressWarnings("unused")
        Task task = new Task("mock desc");

        return;
    }

    @Test
    public void task_whenNotDone_shouldDisplayCross() {
        Task task = new Task("mock desc");
        assertEquals(task.getStatusIcon(), "\u2718");
    }

    @Test
    public void task_whenDone_shouldDisplayTick() {
        Task task = new Task("mock desc");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }
}
