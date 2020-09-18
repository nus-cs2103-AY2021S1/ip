import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void doneTask_validInput_success() throws DukeException {
        Task task = new Todo("Todo");
        task.setDone();
        assertTrue(task.isDone);

        task = new Event("Event", "4/3/2014", "1543");
        task.setDone();
        assertTrue(task.isDone);
    }

    @Test
    public void undoneTask_validInput_success() throws DukeException {
        Task deadline = new Deadline("Deadline", "20/5/1984", "1239");
        assertFalse(deadline.isDone);
    }
}
