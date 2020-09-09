package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoBody_nonEmptyBody_success() {
        assertEquals(Todo.newTodo("Read book").getDescription(), "Read book");
    }
}
