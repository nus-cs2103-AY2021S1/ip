import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tasks.Event;

public class EventTest {

    private static DateTimeFormatter dateTimeFormatter;
    private static LocalDateTime localDateTime;

    @BeforeAll
    static void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        localDateTime = LocalDateTime.parse("2020-08-23 18:00", dateTimeFormatter);
    }
    @Test
    public void constructorTest() {
        Event event = new Event("kiwis", localDateTime);
        assertEquals(
                "[E][" + "\u2718" + "] kiwis (at: 18:00 Aug 23 2020)", event.toString());
    }

    @Test
    public void completionTest() {
        Event event = new Event("kiwis", localDateTime);
        event.completeTask();
        assertEquals("[E][" + "\u2713" + "] kiwis (at: 18:00 Aug 23 2020)", event.toString());
    }

}
