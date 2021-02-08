package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void recordString() {
        ToDo readBook = new ToDo("read book");
        ToDo notReadBook = new ToDo("not read book");
        readBook.setDone();
        assertEquals("[T][✓] read book", readBook.toString());
        assertEquals("[T][✘] not read book", notReadBook.toString());
    }

    @Test
    void testToString() {
        ToDo readBook = new ToDo("read book");
        ToDo notReadBook = new ToDo("not read book");
        readBook.setDone();
        assertEquals("[T][✓] read book", readBook.toString());
        assertEquals("[T][✘] not read book", notReadBook.toString());
    }
}

