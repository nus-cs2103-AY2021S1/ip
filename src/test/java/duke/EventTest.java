package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void recordString() {
        Event readBook = new Event("read book", "2020-01-01");
        Event notReadBook = new Event("not read book", "2020-01-01");
        readBook.setDone();
        assertEquals("[E][✓] read book (at: Jan 01 2020)", readBook.formattedDateString());
        assertEquals("[E][✘] not read book (at: Jan 01 2020)", notReadBook.formattedDateString());
    }

    @Test
    void testToString() {
        Event readBook = new Event("read book", "2020-01-01");
        Event notReadBook = new Event("not read book", "2020-01-01");
        readBook.setDone();
        assertEquals("[E][✓] read book (at: 2020-01-01)", readBook.toString());
        assertEquals("[E][✘] not read book (at: 2020-01-01)", notReadBook.toString());
    }
}

