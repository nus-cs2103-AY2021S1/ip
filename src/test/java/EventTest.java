import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    static DateTimeFormatter dtf;
    static LocalDateTime ldt;

    @BeforeAll
    static void init() {
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ldt = LocalDateTime.parse("2020-08-23 18:00",dtf);
    }
    @Test
    public void constructorTest() {
        Events events = new Events("kiwis", ldt);
        assertEquals(
                "[E]["+"\u2718"+"] kiwis (at: 18:00 Aug 23 2020)",events.toString());
    }

    @Test
    public void completionTest() {
        Events events = new Events("kiwis", ldt);
        events.completeTask();
        assertEquals("[E]["+"\u2713"+"] kiwis (at: 18:00 Aug 23 2020)",events.toString());
    }

}
