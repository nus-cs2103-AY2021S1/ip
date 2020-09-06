package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testStringConversion() {
        assertEquals(
                "[E][\u2717] borrow book (at: Aug 25 2020 02:30 PM to Aug 25 2020 04:30 PM)",
                new Event(
                        "borrow book",
                        LocalDateTime.parse("2020-08-25T14:30"),
                        LocalDateTime.parse("2020-08-25T16:30"))
                        .toString()
        );
        assertEquals(
                "[E][\u2713] return book (at: Aug 31 2020 08:00 PM to Aug 31 2020 09:00 PM)",
                new Event(
                        "return book",
                        true,
                        LocalDateTime.parse("2020-08-31T20:00"),
                        LocalDateTime.parse("2020-08-31T21:00"))
                        .toString()
        );
    }

    @Test
    void storeTest() {
        assertEquals(
                "E F borrow book /at 2020-08-25T14:30 to 2020-08-25T18:00",
                new Event("borrow book",
                        LocalDateTime.parse("2020-08-25T14:30"),
                        LocalDateTime.parse("2020-08-25T18:00"))
                        .store()
        );
        assertEquals(
                "E T return book /at 2020-08-31T20:00 to 2020-08-31T22:35",
                new Event(
                        "return book",
                        true,
                        LocalDateTime.parse("2020-08-31T20:00"),
                        LocalDateTime.parse("2020-08-31T22:35"))
                        .store()
        );
    }
}
