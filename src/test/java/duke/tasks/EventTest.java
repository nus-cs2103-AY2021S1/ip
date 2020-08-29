package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] work hard (at: 12月 12 2020 12 : 21)",
                new Event("work hard",
                        LocalDateTime.of(2020, 12, 12, 12, 21)).toString());
    }

    @Test
    public void testGetStart() {
        assertEquals(LocalDateTime.of(2020, 11, 11, 11, 11),
                new Event("study",
                        LocalDateTime.of(2020, 11, 11, 11, 11)).getStartTime());

        assertEquals(LocalDateTime.of(1999, 8, 11, 01, 11),
                new Event("study",
                        LocalDateTime.of(1999, 8, 11, 01, 11)).getStartTime());
    }
}
