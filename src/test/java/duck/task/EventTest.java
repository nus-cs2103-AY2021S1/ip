package duck.task;

import duck.ui.Colour;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventCreated() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        String expectedFormattedDate = fixedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Event event = new Event("read book", fixedDate);
        assertEquals(event.getDone(), false);
        assertEquals(event.getDescription(), "read book");
        assertEquals(event.getDate(), fixedDate);
        assertEquals(event.getDateAsString(), expectedFormattedDate);
        assertEquals(event.getStatus(), Colour.Cyan("[E]")
                + Colour.Red("[\u2718] read book")
                + " (by: " + expectedFormattedDate + ")");
    }

    @Test
    public void deadlineCanMarkDone() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        Event event = new Event("read book", fixedDate);
        assertEquals(event.getDone(), false);
        event.markDone();
        assertEquals(event.getDone(), true);
    }
}
