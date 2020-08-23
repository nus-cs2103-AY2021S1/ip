package task.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void correctTodoRepresentation(){
        Todo todo = new Todo("Go to the gym");
        assertEquals("[T][N] Go to the gym", todo.toString());
        assertEquals("N", todo.getStatusIcon());
    }

    @Test
    public void testDone(){
        Todo todo = new Todo("Go to the gym");
        todo.markAsDone();
        assertEquals("[T][Y] Go to the gym", todo.toString());
        assertEquals("Y", todo.getStatusIcon());
    }
}
