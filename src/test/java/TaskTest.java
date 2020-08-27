import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getTaskDescription (){
        assertEquals("Borrow book", new Task("Borrow book").getDescription());
    }

    @Test
    public void getDoneStatus() {
        assertEquals(false, new Task("Borrow book").getDoneStatus());
    }

    @Test
    public void getDoneStatusAfterMarkAsCompleted() {
        Task task = new Task("Borrow book");
        task.markAsDone();
        assertEquals(true, task.getDoneStatus());
    }
}