package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-06-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String expected = "[D][✗] return book (by: Jun 06 2020)";
        assertEquals(expected, deadline.toString());
    }
}
