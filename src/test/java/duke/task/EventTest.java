package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Event event = new Event("event1", false, schedule);
        assertEquals("[E][✘] event1 (at: Aug 20 2020)", event.toString());
    }

    @Test
    public void getScheduleTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Event event = new Event("event1", false, schedule);
        assertEquals("Aug 20 2020", event.getSchedule());
    }

    @Test
    public void completeTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Event nonCompletedEvent = new Event("event1", false, schedule);
        Event completedEvent = new Event("event1", true, schedule);
        assertEquals(completedEvent.toString(), nonCompletedEvent.complete().toString());
    }

    @Test
    public void formatTest() {
        LocalDate schedule = LocalDate.parse("2020-08-20");
        Event event = new Event("event1", false, schedule);
        assertEquals("E | 0 | event1 | Aug 20 2020", event.format());
    }
}
