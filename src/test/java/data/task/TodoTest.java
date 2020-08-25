package data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    final Todo todo = new Todo("read book");
    final Todo completedTodo = new Todo(true,"read book");

    @Test
    void fileFormat() {
        assertEquals("T/X/read book", todo.fileFormat());
        assertEquals("T/O/read book", completedTodo.fileFormat());
    }

    @Test
    void testToString() {
        assertEquals("[T][X] read book", todo.toString());
        assertEquals("[T][O] read book", completedTodo.toString());
    }

}