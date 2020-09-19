
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTaskException;
import duke.tasks.Deadline;


public class DeadlineTest {
    @Test
    public void toString_wrongDateFormat_inproperDateFormat() {
        try {
            Deadline task = new Deadline("hi", "01");
            String expected = "[D][✗] hi (by: 01)";
            assertEquals(expected, task.toString());
        } catch (DukeInvalidTaskException | DukeInvalidDateException e) {
            e.printStackTrace();
        }
    }
}
