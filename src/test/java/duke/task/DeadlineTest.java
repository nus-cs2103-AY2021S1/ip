package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final Deadline deadline1 = new Deadline("example 1", false,
            LocalDateTime.of(2020, 12, 12, 12, 12));
    private final Deadline deadline2 = new Deadline("example 2", true,
            LocalDateTime.of(2020, 1, 1, 0, 0));

    @Test
    public void testTodoToString() {
        assertEquals("[D][✗] example 1 (by: Dec 12 2020 1212)", deadline1.toString());
        assertEquals("[D][✓] example 2 (by: Jan 01 2020 0000)", deadline2.toString());
    }

    @Test
    public void testTodoToSaveString() {
        assertEquals("D|0|example 1|2020-12-12T12:12", deadline1.toSaveString());
        assertEquals("D|1|example 2|2020-01-01T00:00", deadline2.toSaveString());
    }
}
