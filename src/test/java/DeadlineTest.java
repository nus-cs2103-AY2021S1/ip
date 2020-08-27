import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime atLocalDate = LocalDateTime.parse("2020-12-12 12:30", formatter);

    @Test
    public void markAsDone_success() {
        Deadline task = new Deadline("proj meeting", atLocalDate);
        assertEquals("\u2718", task.getStatusIcon());
        task = task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void toString_success() {
        Deadline task = new Deadline("proj meeting", atLocalDate);
        assertEquals("[D][" + "\u2718" + "] " + "proj meeting (by: Dec 12 2020 12:30 PM)", task.toString());

        Deadline doneTask = new Deadline("proj meeting", atLocalDate, true);
        assertEquals("[D][" + "\u2713" + "] " + "proj meeting (by: Dec 12 2020 12:30 PM)", doneTask.toString());
    }

    @Test
    public void toTxtFileformat_success() {
        Deadline task = new Deadline("proj meeting", atLocalDate);
        assertEquals("D | 0 | proj meeting | 2020-12-12 12:30", task.toTxtFileFormat());

        Deadline doneTask = new Deadline("proj meeting", atLocalDate, true);
        assertEquals("D | 1 | proj meeting | 2020-12-12 12:30", doneTask.toTxtFileFormat());
    }
}
