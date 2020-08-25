package duck.task;

import duck.ui.Colour;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineCreated() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        String expectedFormattedDate = fixedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Deadline deadline = new Deadline("read book", fixedDate);
        assertEquals(deadline.getDone(), false);
        assertEquals(deadline.getDescription(), "read book");
        assertEquals(deadline.getDate(), fixedDate);
        assertEquals(deadline.getDateAsString(), expectedFormattedDate);
        assertEquals(deadline.getStatus(), Colour.Magenta("[D]")
                + Colour.Red("[\u2718] read book")
                + " (by: " + expectedFormattedDate + ")");
    }

    @Test
    public void deadlineCanMarkDone() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        Deadline deadline = new Deadline("read book", fixedDate);
        assertEquals(deadline.getDone(), false);
        deadline.markDone();
        assertEquals(deadline.getDone(), true);
    }
}
