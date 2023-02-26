import duke.util.DukeException;
import duke.tasks.EventTask;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;

/**
 * Testing class for EventTask object.
 */
public class EventTaskTest {
    @Test
    public void StringAndFileFormatTest() throws DukeException {
        EventTask task1 = new EventTask("dinner", "2012-02-21 1900");
        assertEquals("[E] [\u2718] (P: nil) dinner (at: 21 FEBRUARY 2012)", task1.toString());
        assertEquals("E | 0 | nil | dinner | 21 FEBRUARY 2012", task1.fileString());
    }

    @Test
    public void MarkDoneTest() throws DukeException {
        EventTask task1 = new EventTask("Company Lunch", "2019-03-23 2000");
        assertEquals("[E] [\u2718] (P: nil) Company Lunch (at: 23 MARCH 2019)", task1.toString());
        task1.markDone();
        assertEquals("[E] [\u2713] (P: nil) Company Lunch (at: 23 MARCH 2019)", task1.toString());
    }
}
