package duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    final Todo todo = new Todo("read book");
    final Todo completedTodo = new Todo(true, "read book");

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
