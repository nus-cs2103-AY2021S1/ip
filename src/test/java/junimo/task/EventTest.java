package junimo.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void constructor() {
        // Test with correct arguments
        Event event1 = new Event("Eat Apple", "2020-12-12 12:00", "2020-12-12 13:00",
                false);
        Event event2 = new Event("Write paper", "2020-05-05 12:00", "2020-05-06 23:59",
                true);

        assertEquals("Eat Apple", event1.description);
        assertEquals(LocalDateTime.parse("2020-12-12 12:00", Event.PARSER_FORMATTER), event1.startAt);
        assertEquals(LocalDateTime.parse("2020-12-12 13:00", Event.PARSER_FORMATTER), event1.endAt);
        assertEquals(false, event1.isDone);
        assertEquals("Write paper", event2.description);
        assertEquals(LocalDateTime.parse("2020-05-05 12:00", Event.PARSER_FORMATTER), event2.startAt);
        assertEquals(LocalDateTime.parse("2020-05-06 23:59", Event.PARSER_FORMATTER), event2.endAt);
        assertEquals(true, event2.isDone);
    }

    @Test
    public void getParsedTask() {
        Event event = new Event("Eat Apple", "2020-12-12 12:00", "2020-12-12 13:00", true);
        String expectedParsedTask = "event Eat Apple /start 2020-12-12 12:00 /end 2020-12-12 13:00"
                + System.lineSeparator() + "true" + System.lineSeparator();
        assertEquals(expectedParsedTask, event.getParsedTask());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("Eat Apple", "2020-12-12 12:00", "2020-12-12 13:00", true);
        String expectedToString = "[E][\u2713] Eat Apple (at: Dec 12 2020 12:00PM to 1:00PM)";
        assertEquals(expectedToString, event.toString());

        Event event1 = new Event("Write paper", "2020-05-05 12:00", "2020-05-06 23:59", true);
        String expectedToString1 = "[E][\u2713] Write paper (at: May 05 2020 12:00PM to May 06 2020 11:59PM)";
        assertEquals(expectedToString1, event1.toString());
    }

    @Test
    public void equals() {
        Event event1 = new Event("Eat Apple", "2020-12-12 12:00", "2020-12-12 13:00", true);
        Event event2 = new Event("Eat Apple", "2020-12-12 12:00", "2020-12-12 13:00", true);
        assertEquals(event1, event2);
    }
}
