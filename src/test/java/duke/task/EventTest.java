package duke.task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("birthday", LocalDateTime.parse("2020-09-30T23:59:00"));
        assertEquals("[E][\u2718] birthday (at: Sep 30 2020 23:59)", event.toString());
    }
}
