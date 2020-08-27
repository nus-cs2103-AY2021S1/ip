import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void markAsDone_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime byLocalDate = LocalDateTime.parse("2020-12-12 12:30", formatter);
        Event task = new Event("proj meeting", byLocalDate);
        assertEquals("\u2718", task.getStatusIcon());
        task = task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void toString_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime byLocalDate = LocalDateTime.parse("2020-12-12 12:30", formatter);
        Event task = new Event("proj meeting", byLocalDate);
        assertEquals("[E][" + "\u2718" + "] " + "proj meeting (at: Dec 12 2020 12:30 PM)", task.toString());

        Event doneTask = new Event("proj meeting", byLocalDate, true);
        assertEquals("[E][" + "\u2713" + "] " + "proj meeting (at: Dec 12 2020 12:30 PM)", doneTask.toString());
    }

    @Test
    public void toTxtFileformat_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime byLocalDate = LocalDateTime.parse("2020-12-12 12:30", formatter);
        Event task = new Event("proj meeting", byLocalDate);
        assertEquals("E | 0 | proj meeting | 2020-12-12 12:30", task.toTxtFileFormat());

        Event doneTask = new Event("proj meeting", byLocalDate, true);
        assertEquals("E | 1 | proj meeting | 2020-12-12 12:30", doneTask.toTxtFileFormat());
    }
}
