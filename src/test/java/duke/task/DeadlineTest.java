package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline task = new Deadline("return book", LocalDate.parse("2020-08-29"));
        assertEquals("[D][\u2718] return book (by: Aug 29 2020)", task.toString());
        task.done();
        assertEquals("[D][\u2713] return book (by: Aug 29 2020)", task.toString());
    }
}