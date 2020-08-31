import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidTaskException;
import duke.tasks.Todo;



public class TodoTest {
    @Test
    public void initializeTodo() {
        try {
            Todo task = new Todo("hi");
            String expected = "[T]" + "[✗] hi";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException e) {
            e.printStackTrace();
        }
    }
}
