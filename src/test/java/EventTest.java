import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void printFormat_taskNotDone() {
        Event testEvent = new Event("test", Priority.MEDIUM, "2019-10-15 08:00");
        assertEquals(testEvent.toString(), "[E][\u2718] {MEDIUM} test (at: 2019-10-15 08:00)");
    }

    @Test
    public void printFormat_taskDone() {
        Event testEvent = new Event("test", Priority.HIGH, "2019-10-15 08:00", true);
        assertEquals(testEvent.toString(), "[E][\u2713] {HIGH} test (at: 2019-10-15 08:00)");
    }

    @Test
    public void printFormat_localDateEvent_taskNotDone() {
        LocalDate testDate = LocalDate.parse("2019-10-15");
        Event testEvent = new Event("test", Priority.LOW, testDate);
        assertEquals(testEvent.toString(), "[E][\u2718] {LOW} test (at: Oct 15 2019)");
    }
}
