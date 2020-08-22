package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testTaskToText() {
        assertEquals("E|0|meeting|2020-08-25T14:00", new Event("meeting",
                LocalDateTime.parse("2020-08-25T14:00")).taskToText());
    }
}
