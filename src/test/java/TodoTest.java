import main.java.duke.exception.DukeInvalidTaskException;
import main.java.duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void initializeTodo(){
        try {
            Todo task = new Todo("hi");
            String expected = "[T]" + "[✗] hi";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException e) {
            e.printStackTrace();
        }
    }
}
