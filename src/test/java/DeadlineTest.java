import main.java.Deadline;
import main.java.DukeInvalidDateException;
import main.java.DukeInvalidTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void Test(){
        try {
            Deadline task = new Deadline("hi", "2020-01-01");
            String expected = "[D][✗] hi (by: Jan 1 2020)";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException | DukeInvalidDateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test2(){
        try {
            Deadline task = new Deadline("hi", "01");
            String expected = "[D][✗] hi (by: 01)";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException | DukeInvalidDateException e) {
            e.printStackTrace();
        }
    }
}
