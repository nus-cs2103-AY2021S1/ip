package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void recordString() {
        Deadline readBook = new Deadline("read book", "2020-01-01");
        Deadline notReadBook = new Deadline("not read book", "2020-01-01");
        readBook.setDone();
        assertEquals("[D][✓] read book (by: Jan 01 2020)", readBook.recordString());
        assertEquals("[D][✘] not read book (by: Jan 01 2020)", notReadBook.recordString());
    }

    @Test
    void testToString() {
        Deadline readBook = new Deadline("read book", "2020-01-01");
        Deadline notReadBook = new Deadline("not read book", "2020-01-01");
        readBook.setDone();
        assertEquals("[D][✓] read book (by: 2020-01-01)", readBook.toString());
        assertEquals("[D][✘] not read book (by: 2020-01-01)", notReadBook.toString());
    }
}