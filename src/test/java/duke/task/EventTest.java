package duke.task;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEvent() {
        try {
            Event e = new Event("project meeting",
                    new SimpleDateFormat("dd/MM/yyyy HHmm")
                            .parse("05/12/2020 1600"), true, false);
            assertEquals("[E][✘] project meeting (at: Dec 05 2020 04:00pm)", e.toString());
        } catch (Exception e) {}
    }
}
