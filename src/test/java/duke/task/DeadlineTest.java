package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void testStringConversion() {
        assertEquals(
                "[D][✗] borrow book (by: Aug 25 2020 02:30 PM)",
                new Deadline("borrow book", LocalDateTime.parse("2020-08-25T14:30")).toString());
        assertEquals(
                "[D][✓] return book (by: Aug 31 2020 08:00 PM)",
                new Deadline("return book", true, LocalDateTime.parse("2020-08-31T20:00")).toString());
    }

    @Test
    void storeTest() {
        assertEquals(
                "D F borrow book /by 2020-08-25T14:30",
                new Deadline("borrow book", LocalDateTime.parse("2020-08-25T14:30")).store());
        assertEquals(
                "D T return book /by 2020-08-31T20:00",
                new Deadline("return book", true, LocalDateTime.parse("2020-08-31T20:00")).store());
    }
}
