package duke;

import main.java.duke.Deadline;
import main.java.duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStringTest1() {
        Event event = new Event("Complete report /by 2019-10-15 0823 /priority high");
        assertEquals("[E][\u2718][HIGH] Complete report (by: OCTOBER 15 2019, 08:23 AM)", event.toString());
    }

    @Test
    public void toStringTest2() {
        Event event = new Event("GEH meeting");
        assertEquals("[E][\u2718] GEH meeting", event.toString());
    }

}
