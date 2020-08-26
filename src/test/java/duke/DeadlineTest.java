package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTest() {
        String deadlineDateTime = "2020-12-10 1230";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime localDateTime = LocalDateTime.parse(deadlineDateTime, formatter);
        assertEquals(new Deadline("Study", localDateTime).toString(), "[D][✘] Study (by: Dec 10 2020, 12:30 pm)");
    }

}
