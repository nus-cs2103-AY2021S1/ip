package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void txtFileFormatTest() {
        Deadline deadline = new Deadline("deadlinetest", LocalDate.parse("2020-08-26"), LocalTime.parse("18:00"));
        assertEquals("D ~/~ 0 ~/~ deadlinetest ~/~ 2020-08-26 ~/~ 18:00", deadline.txtFileFormat());
    }
}
