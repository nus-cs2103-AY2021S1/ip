package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testToString() {
        Task readBook = new Task("read book");
        Task notReadBook = new Task("not read book");
        readBook.setDone();
        assertEquals("[✓] read book", readBook.toString());
        assertEquals("[✘] not read book", notReadBook.toString());
    }
}