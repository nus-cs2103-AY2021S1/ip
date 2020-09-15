package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void factoryMethod_validArguments_correctAttributes() {
        Todo todo = Todo.newTodo("Read book");
        assertEquals(todo.getDescription(), "Read book");
        assertEquals(todo.getStatus(), "0");
        assertEquals(todo.getType(), "T");
    }
}
