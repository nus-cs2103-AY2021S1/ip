package chattybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markDone_taskMarkAsDone_taskStatusShowsTick() {
        Task testTask = new Task("Testing");
        testTask.markDone();
        assertEquals("[" + "Completed" + "] Testing", testTask.toString());
    }
}
