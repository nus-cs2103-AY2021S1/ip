import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void printFormat_taskNotDone() {
        Event testEvent = new Event("test", "2019-10-15 08:00");
        assertEquals(testEvent.toString(), "[E][\u2718] test (at: 2019-10-15 08:00)");
    }

    @Test
    public void printFormat_taskDone() {
        Event testEvent = new Event("test", "2019-10-15 08:00", true);
        assertEquals(testEvent.toString(), "[E][\u2713] test (at: 2019-10-15 08:00)");
    }

    @Test
    public void printFormat_localDateEvent_taskNotDone() {
        LocalDate testDate = LocalDate.parse("2019-10-15");
        Event testEvent = new Event("test", testDate);
        assertEquals(testEvent.toString(), "[E][\u2718] test (at: Oct 15 2019)");
    }
}
