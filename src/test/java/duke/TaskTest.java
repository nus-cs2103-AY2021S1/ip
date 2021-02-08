package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

