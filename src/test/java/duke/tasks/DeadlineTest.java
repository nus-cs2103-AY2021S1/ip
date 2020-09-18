package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testHasDateTimeElapsed() {
        LocalDateTime currentTime = LocalDateTime.now();
        Deadline upcomingDeadline = new Deadline("Project Meeting", currentTime.plusSeconds(10));
        assertFalse(upcomingDeadline.hasDateTimeElapsed());
        Deadline overdueDeadline = new Deadline("Project Meeting", currentTime);
        assertTrue(overdueDeadline.hasDateTimeElapsed());
    }

    @Test
    public void testCompareTo() {
        Event firstDeadline = new Event("First Deadline", LocalDateTime.parse("2020-08-25T09:00:00"));
        Event secondDeadline = new Event("Second Deadline", LocalDateTime.parse("2020-08-25T10:00:00"));
        Event thirdDeadline = new Event("Third Deadline", LocalDateTime.parse("2020-08-25T11:00:00"));
        assertTrue(firstDeadline.compareTo(secondDeadline) < 0);
        assertTrue(secondDeadline.compareTo(thirdDeadline) < 0);
        assertTrue(firstDeadline.compareTo(thirdDeadline) < 0);
        assertTrue(secondDeadline.compareTo(secondDeadline) == 0);
        assertTrue(thirdDeadline.compareTo(firstDeadline) > 0);
        assertTrue(thirdDeadline.compareTo(firstDeadline) > 0);
    }
}
