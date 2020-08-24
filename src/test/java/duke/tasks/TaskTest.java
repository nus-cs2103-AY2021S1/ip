package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markDone_taskMarkAsDone_taskStatusShowsTick(){
        Task testTask = new Task("Testing");
        testTask.markDone();
        assertEquals("[" + "\u2713" + "] Testing", testTask.toString());
    }
}
