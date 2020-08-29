import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringTest3() {
        assertEquals(new Deadline(true, "final exam", LocalDateTime.parse("2020-12-29 09:00", formatter))
                        .toString(),
                "[D][\u2713] final exam (by: Dec 29 2020 09:00)");
    }
}
