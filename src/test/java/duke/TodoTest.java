package duke;

import main.java.duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testingStringConversion() {
        Todo todo = new Todo("return book");
        assertEquals("[T][✘] return book", todo.toString());
        todo.markAsDone();
        assertEquals("[T][✓] return book", todo.toString());
    }
    
    @Test
    public void testingStringFileConversion() {
        Todo todo = new Todo("return book");
        assertEquals("T | 0 | return book",todo.toStringFile());
    }
}
