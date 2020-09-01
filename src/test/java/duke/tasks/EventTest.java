package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testHasDateTimeElapsed() {
        LocalDateTime currentTime = LocalDateTime.now();
        Event upcomingEvent = new Event("CS2103T Lecture", currentTime.plusSeconds(10));
        assertFalse(upcomingEvent.hasDateTimeElapsed());
        Event overdueEvent = new Event("CS2103T Lecture", currentTime.minusSeconds(10));
        assertTrue(overdueEvent.hasDateTimeElapsed());
    }

    @Test
    public void testCompareTo() {
        Event firstEvent = new Event("First Event", LocalDateTime.parse("2020-08-25T09:00:00"));
        Event secondEvent = new Event("Second Event", LocalDateTime.parse("2020-08-25T10:00:00"));
        Event thirdEvent = new Event("Third Event", LocalDateTime.parse("2020-08-25T11:00:00"));
        assertTrue(firstEvent.compareTo(secondEvent) < 0);
        assertTrue(secondEvent.compareTo(thirdEvent) < 0);
        assertTrue(firstEvent.compareTo(thirdEvent) < 0);
        assertTrue(secondEvent.compareTo(secondEvent) == 0);
        assertTrue(thirdEvent.compareTo(firstEvent) > 0);
        assertTrue(thirdEvent.compareTo(firstEvent) > 0);
    }
}
