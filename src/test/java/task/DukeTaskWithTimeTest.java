package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.CommonString;

import duke.task.DukeTaskWithTime;
import stub.DukeTaskWithTimeStub;


public class DukeTaskWithTimeTest {
    @Test
    public void dateTimeFormatTest() {
        LocalDateTime dateTime = LocalDateTime.of(2012, 12, 12, 12, 12, 12);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        DukeTaskWithTime task = new DukeTaskWithTimeStub("TESTING", dateTime);

        assertEquals(task.getDateTime(), df.format(dateTime));
    }
}
