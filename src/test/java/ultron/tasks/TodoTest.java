package ultron.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    /**
     * Test if the getCommandMethod is correct.
     */
    @Test
    public void getCommandTestString() {
        assertEquals("hello", new Todo("hello").getCommand());
    }
    /**
     * Test Parsing functionality of todo class.
     */
    @Test
    public void parseCommandTest() {
        Task todo = Todo.parseCommand("hello");
        assertEquals("hello", todo.getMessage());
        assertEquals("TODO", todo.getType());
        assertEquals("\u2718", todo.getStatusIcon());
        assertEquals("[T][\u2718] hello", todo.toString());
    }
}
