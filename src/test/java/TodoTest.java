import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.Todo;

public class TodoTest {
    @Test
    public void toDataTest() {
        Todo todo = new Todo("something");
        assertEquals("T | false | something", todo.toData());
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("something");
        assertEquals("[T][✗] something", todo.toString());
    }
}
