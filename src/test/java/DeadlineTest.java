import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tasks.Deadline;


public class DeadlineTest {

    private static DateTimeFormatter dateTimeFormat;
    private static LocalDateTime localDateTime;

    @BeforeAll
    static void init() {
        dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        localDateTime = LocalDateTime.parse("2020-08-23 18:00", dateTimeFormat);
    }
    @Test
    public void constructorTest() {
        Deadline events = new Deadline("kiwis", localDateTime);
        assertEquals(
                "[D][" + "\u2718" + "] kiwis (by: 18:00 Aug 23 2020)", events.toString());
    }

    @Test
    public void completionTest() {
        Deadline events = new Deadline("kiwis", localDateTime);
        events.completeTask();
        assertEquals("[D][" + "\u2713" + "] kiwis (by: 18:00 Aug 23 2020)", events.toString());
    }
}
