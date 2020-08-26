package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-09-30"));
        assertEquals("[D][\u2718] return book (by: Sep 30 2020)", deadline.toString());
    }
}
