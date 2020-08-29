package main.java.duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString_eventWithDescription_suitableDescription() {
        LocalDate localDate = LocalDate.parse("2014-12-07");
        assertEquals("[E][\u2718] a b c (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")", new Event("a b c", localDate).toString());
    }

    @Test
    void record_eventWithDescription_suitableDescription() {
        LocalDate localDate = LocalDate.parse("2014-12-07");
        assertEquals("E | 1 | a b c | 2014-12-07", new Event("a b c", localDate).record());
    }

    @Test
    void isAt_eventWithDate_eventIsAtTheTime() {
        LocalDate localDate = LocalDate.parse("2014-12-07");
        Event event = new Event("a b c", localDate);
        assertTrue(event.isAt(localDate));
    }
}