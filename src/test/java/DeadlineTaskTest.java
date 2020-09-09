import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.CommonString;
import duke.task.DeadlineTask;


public class DeadlineTaskTest {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private static final String description = "TESTING";
    @Test
    public void deadlineStringTest() {
        LocalDateTime dateTime = LocalDateTime.of(2012, 12, 12, 12, 12, 12);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        String dateTimeString = df.format(dateTime);

        DeadlineTask task = new DeadlineTask("TESTING", dateTime);
        assertEquals(task.toString(), "[D]" + "[" + cross + "] "
                + description + String.format(" (by: %s)", dateTimeString));
        task.markAsDone();
        assertEquals(task.toString(), "[D]" + "[" + tick + "] "
                + description + String.format(" (by: %s)", dateTimeString));
    }
}
