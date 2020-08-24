import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
        public void testTodo() {
            Task test = new Todo(false, 1, "Wash Clothes");
            assertEquals("[T][✗] Wash Clothes", test.toString());
    }

    @Test
    public void testDeadline() {
        Task test = new Deadline(false, 1, "Wash Clothes", "2020-10-23");
        assertEquals("[D][✗] Wash Clothes (by: 23 Oct 2020)", test.toString());
    }

    @Test
    public void testMarkDone() {
        Task test = new Event(false, 1, "Wash Car", "2020-11-19");
        test = test.markDone();
        assertEquals("[E][✓] Wash Car (at: 19 Nov 2020)", test.toString());
    }
}
