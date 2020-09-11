package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void testStringConversion() {
        assertEquals(
                "[D][\u2717] borrow book (by: Aug 25 2020 02:30 PM)",
                new Deadline("borrow book", LocalDateTime.parse("2020-08-25T14:30")).toString());
        assertEquals(
                "[D][\u2713] return book (by: Aug 31 2020 08:00 PM)",
                new Deadline("return book", true, LocalDateTime.parse("2020-08-31T20:00")).toString());
    }

    @Test
    void store() {
        assertEquals(
                "D F borrow book /by 2020-08-25T14:30",
                new Deadline("borrow book", LocalDateTime.parse("2020-08-25T14:30")).store());
        assertEquals(
                "D T return book /by 2020-08-31T20:00",
                new Deadline("return book", true, LocalDateTime.parse("2020-08-31T20:00")).store());
    }

    @Test
    void compareTo() {
        Deadline deadline1 = new Deadline(
                "return book",
                LocalDateTime.parse("2020-09-15T14:00"));
        Deadline deadline2 = new Deadline(
                "assignment",
                LocalDateTime.parse("2020-09-10T12:30"));
        assertTrue(deadline1.compareTo(deadline2) > 0);
    }
}
