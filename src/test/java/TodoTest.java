import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dude.task.Todo;

public class TodoTest {
    @Test
    public void todoTest_creation_success() {
        Todo todo = new Todo("test");
        assertEquals(todo.toString(), "[T][\u2718] test");
    }
}
