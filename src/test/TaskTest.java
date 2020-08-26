import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void getType() {
        Task task = new Task("study", false, "T");
        assertEquals("T", task.getType());
    }

    @Test
    void markAsDone() {
        Task task = new Task("study", false, "T");
        task.markAsDone();
        assertEquals(true, task.isDone);
    }


}