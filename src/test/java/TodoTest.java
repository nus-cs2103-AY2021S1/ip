import main.java.DukeInvalidTaskException;
import main.java.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void Test(){
        try {
            Todo task = new Todo("hi");
            String expected = "[T]" + "[✗] hi";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException e) {
            e.printStackTrace();
        }
    }
}
