package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void recordString() {
        Deadline readBook = new Deadline("read book", "2020-01-01");
        Deadline notReadBook = new Deadline("not read book", "2020-01-01");
        readBook.setDone();
        assertEquals("[D][✓] read book (by: Jan 01 2020)", readBook.formattedDateString());
        assertEquals("[D][✘] not read book (by: Jan 01 2020)", notReadBook.formattedDateString());
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

