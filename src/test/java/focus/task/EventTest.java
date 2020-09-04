package focus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testTaskToText() {
        assertEquals("E|0|meeting|2020-08-25T14:00", new Event("meeting",
                LocalDateTime.parse("2020-08-25T14:00")).taskToText());
    }
}
