import duke.tasks.Deadline;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_correctDateFormat_properDateFormat(){
        try {
            Deadline task = new Deadline("hi", "2020-01-01");
            String expected = "[D][✗] hi (by: Jan 1 2020)";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException | DukeInvalidDateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toString_wrongDateFormat_inproperDateFormat(){
        try {
            Deadline task = new Deadline("hi", "01");
            String expected = "[D][✗] hi (by: 01)";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException | DukeInvalidDateException e) {
            e.printStackTrace();
        }
    }
}
