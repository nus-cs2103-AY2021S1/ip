package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("birthday", LocalDate.parse("2020-09-30"));
        assertEquals("[E][\u2718] birthday (at: Sep 30 2020)", event.toString());
    }
}
