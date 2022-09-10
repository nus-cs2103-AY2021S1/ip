package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    private Event testEvent = new Event("buy cake", LocalDate.parse("2009-11-27"));

    @Test
    public void descriptionTest() {
        assertEquals(testEvent.description, "buy cake");
    }

    @Test
    public void toStringTest() {
        assertEquals(testEvent.toString(), "[E][✘] buy cake (at:27 Nov 2009)");
    }
}
