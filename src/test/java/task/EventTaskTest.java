package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.CommonString;
import duke.task.EventTask;


public class EventTaskTest {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private static final String description = "TESTING";
    @Test
    public void eventStringTest() {
        LocalDateTime dateTime = LocalDateTime.of(2012, 12, 12, 12, 12, 12);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        String dateTimeString = df.format(dateTime);
        EventTask task = new EventTask("TESTING", dateTime);
        assertEquals(task.toString(), "[E]" + "[" + cross + "] "
                + description + String.format(" (at: %s)", dateTimeString));
        task.markAsDone();
        assertEquals(task.toString(), "[E]" + "[" + tick + "] "
                + description + String.format(" (at: %s)", dateTimeString));
    }
}
