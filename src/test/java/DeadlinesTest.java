import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    static DateTimeFormatter dtf;
    static LocalDateTime ldt;

    @BeforeAll
    static void init() {
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ldt = LocalDateTime.parse("2020-08-23 18:00",dtf);
    }
    @Test
    public void constructorTest() {
        Deadlines events = new Deadlines("kiwis", ldt);
        assertEquals(
                "[D]["+"\u2718"+"] kiwis (by: 18:00 Aug 23 2020)",events.toString());
    }

    @Test
    public void completionTest() {
        Deadlines events = new Deadlines("kiwis", ldt);
        events.completeTask();
        assertEquals("[D]["+"\u2713"+"] kiwis (by: 18:00 Aug 23 2020)",events.toString());
    }
}
