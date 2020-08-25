package data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    final LocalDateTime dateTime = LocalDateTime.parse("2020-10-18T14:30");
    final Deadline deadline = new Deadline("return book", dateTime);
    final Deadline completeDeadline = new Deadline(true,"return book", dateTime);

    @Test
    void fileFormat() {
        assertEquals("D/X/return book/Sun, 18 Oct 2020 02:30 PM", deadline.fileFormat());
        assertEquals("D/O/return book/Sun, 18 Oct 2020 02:30 PM", completeDeadline.fileFormat());
    }

    @Test
    void testToString() {
        assertEquals("[D][X] return book (by: Sun, 18 Oct 2020 02:30 PM)", deadline.toString());
        assertEquals("[D][O] return book (by: Sun, 18 Oct 2020 02:30 PM)", completeDeadline.toString());
    }

}