import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void getTaskDescription () {
        assertEquals("Borrow book", new ToDo("Borrow book").getDescription());
    }

    @Test
    public void getDoneStatus() {
        assertEquals(false, new ToDo("Borrow book").getDoneStatus());
    }

    @Test
    public void getDoneStatusAfterMarkAsCompleted() {
        Task task = new ToDo("Borrow book");
        task.markAsDone();
        assertEquals(true, task.getDoneStatus());
    }
}