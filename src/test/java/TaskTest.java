import static org.junit.Assert.assertEquals;

import org.junit.Test;

import duke.task.Task;
import duke.task.ToDo;


public class TaskTest {
    @Test
    public void markAsDone_markUnfinishedTaskAsDone_returnsTick() {
        Task task = new ToDo("test", false, "low");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    public void getDescription_getDescriptionOfLegitimateTask_correctDescription() {
        Task task = new ToDo("return home", false, "high");
        String description = task.getDescription();
        assertEquals(description, "return home");
    }
}
