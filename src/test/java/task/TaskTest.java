package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void testMarkAsDone(){
        Task task = new Task("Example task", false);
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testTickIcon(){
        Task task = new Task("Example task", false);
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    public void testCrossIcon(){
        Task task = new Task("Example task", false);
        assertEquals(task.getStatusIcon(), "\u2718");
    }
}
