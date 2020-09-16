package focus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testTaskToText() {
        assertEquals("D|0|tP|2020-08-23T23:59", new Deadline("tP",
                LocalDateTime.parse("2020-08-23T23:59")).taskToText());
    }
}
