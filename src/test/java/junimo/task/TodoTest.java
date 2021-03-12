package junimo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void constructor() {
        // Test with correct arguments
        Todo todo1 = new Todo("Eat Apple", false);
        Todo todo2 = new Todo("Write paper", true);

        assertEquals("Eat Apple", todo1.description);
        assertEquals(false, todo1.isDone);
        assertEquals("Write paper", todo2.description);
        assertEquals(true, todo2.isDone);
    }

    @Test
    public void getParsedTask() {
        Todo todo = new Todo("Eat Apple", true);
        String expectedParsedTask = "todo Eat Apple" + System.lineSeparator()
                + "true" + System.lineSeparator();
        assertEquals(expectedParsedTask, todo.getParsedTask());
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("Eat Apple", true);
        String expectedToString = "[T][\u2713] Eat Apple";
        assertEquals(expectedToString, todo.toString());
    }

    @Test
    public void equalsTest() {
        Todo todo1 = new Todo("Eat Apple", true);
        Todo todo2 = new Todo("Eat Apple", true);
        assertEquals(todo2, todo1);
    }
}
