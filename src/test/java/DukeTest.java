import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    void testToString() {
        assertEquals("[T][\u2718] return book",
                new Todo("return book").toString());
    }

    @Test
    void testDeadlineToString() {
        assertEquals("[D][\u2718] return book (by: Dec 30 2020)",
                new Deadline("return book", LocalDate.parse("2020-12-30")).toString());
    }

    @Test
    void testEventToString() {
        assertEquals("[E][\u2718] return book (at: Dec 30 2020)",
                new Event("return book", LocalDate.parse("2020-12-30")).toString());
    }

    @Test
    void testTodoToSave() {
        assertEquals("T 0 return book",
                new Todo("return book").toString());
    }

    @Test
    void testDeadlineToSave() {
        assertEquals("D 0 return book/Dec 30 2020",
                new Deadline("return book", LocalDate.parse("2020-12-30")).toString());
    }

    @Test
    void testEventToSave() {
        assertEquals("E 0 return book/Dec 30 2020",
                new Event("return book", LocalDate.parse("2020-12-30")).toString());
    }

}
