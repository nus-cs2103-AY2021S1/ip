package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineCreated() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        String expectedFormattedDate = fixedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Deadline deadline = new Deadline("read book", fixedDate);
        assertEquals(false, deadline.getDone());
        assertEquals("read book", deadline.getDescription());
        assertEquals(fixedDate, deadline.getDate());
        assertEquals(expectedFormattedDate, deadline.getDateAsString());
        assertEquals("[D]"
                + "[\u2718] read book"
                + " (by: " + expectedFormattedDate + ")", deadline.getStatus());
    }

    @Test
    public void deadlineCanMarkDone() {
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        Deadline deadline = new Deadline("read book", fixedDate);
        assertEquals(false, deadline.getDone());
        deadline.markDone();
        assertEquals(true, deadline.getDone());
    }
}
