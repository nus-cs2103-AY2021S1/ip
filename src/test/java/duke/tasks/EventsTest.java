package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] work hard (at: 12月 12 2020 12 : 21)",
                new Events("work hard",
                        LocalDateTime.of(2020, 12, 12, 12, 21)).toString());
    }

    @Test
    public void testGetStart() {
        assertEquals(LocalDateTime.of(2020, 11, 11, 11, 11),
                new Events("study",
                        LocalDateTime.of(2020, 11, 11, 11, 11)).getStart());

        assertEquals(LocalDateTime.of(1999, 8, 11, 01, 11),
                new Events("study",
                        LocalDateTime.of(1999, 8, 11, 01, 11)).getStart());
    }
}
