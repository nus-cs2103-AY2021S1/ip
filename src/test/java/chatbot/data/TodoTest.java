package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    Todo todo = Todo.newTodo("Read book");

    @Test
    public void factoryMethod_validArguments_correctAttributes() {
        assertEquals(todo.getDescription(), "Read book");
        assertEquals(todo.getStatus(), "0");
        assertEquals(todo.getType(), "T");
    }

    @Test
    public void markDone_correctStatus() {
        assertEquals(todo.markDone().getStatus(), "1");
    }
}
