package duke.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2020-09-30T23:59:00"));
        assertEquals("[D][\u2718] return book (by: Sep 30 2020 23:59)", deadline.toString());
    }
}
