package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testStringConversion() {
        assertEquals(
                "[E][✗] borrow book (at: Aug 25 2020 02:30 PM)",
                new Event("borrow book", LocalDateTime.parse("2020-08-25T14:30")).toString());
        assertEquals(
                "[E][✓] return book (at: Aug 31 2020 08:00 PM)",
                new Event("return book", true, LocalDateTime.parse("2020-08-31T20:00")).toString());
    }

    @Test
    void storeTest() {
        assertEquals(
                "E F borrow book /at 2020-08-25T14:30\n",
                new Event("borrow book", LocalDateTime.parse("2020-08-25T14:30")).store());
        assertEquals(
                "E T return book /at 2020-08-31T20:00\n",
                new Event("return book", true, LocalDateTime.parse("2020-08-31T20:00")).store());
    }
}
