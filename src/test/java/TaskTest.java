import duke.task.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void markAsDone_markUnfinishedTaskAsDone_returnsTick() {
        Task task = new Task("test", false);
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    public void getDescription_getDescriptionOfLegitimateTask_correctDescription() {
        Task task = new Task("return home");
        String description = task.getDescription();
        assertEquals(description, "return home");
    }
}
