import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void doneTask_validInput_success() {
        Task task = new Todo("Todo");
        task.setDone();
        assertEquals(task.isDone, true);

        task = new Event("Event", "4/3/2014", "1543");
        task.setDone();
        assertEquals(task.isDone, true);
    }

    @Test
    public void undoneTask_validInput_success() {
        Task deadline = new Deadline("Deadline", "20/5/1984", "1239");
        assertEquals(deadline.isDone, false);
    }
}