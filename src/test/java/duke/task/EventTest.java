package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final Event EVENT_ONE = new Event("test", false, getLocalDate());
    private static final Event EVENT_TWO = new Event("test 2", true, getLocalDate());

    private static LocalDateTime getLocalDate() {
        return LocalDateTime.of(2020, 12, 12, 6, 0);
    }

    @Test
    public void testCreateEvent() {
        assertEquals(Event.createEvent("test", getLocalDate()), EVENT_ONE);
    }

    @Test
    public void testToString() {
        assertEquals(EVENT_ONE.toString(), "[E][\u2717] test (at: 06:00)");
        assertEquals(EVENT_TWO.toString(), "[E][\u2713] test 2 (at: 06:00)");
    }

    @Test
    public void testGetTaskDatetime() {
        assertEquals(EVENT_ONE.getTaskDatetime(), Optional.of("06:00"));
    }
}
