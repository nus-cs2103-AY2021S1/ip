package chatbot.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoBody_nonEmptyBody_success() {
        assertEquals(Todo.newTodo("Read book").getDescription(), "Read book");
    }
}
