import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import task.Event;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][not done] dance auditions (at: 30 September 2020 11:30 AM)",
                new Event("dance auditions", false,
                        LocalDateTime.of(2020, 9, 30, 11, 30)).toString());
    }
}
