package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testTaskToText() {
        assertEquals("D|0|tP|2020-08-23T23:59", new Deadline("tP",
                LocalDateTime.parse("2020-08-23T23:59")).taskToText());
    }
}
