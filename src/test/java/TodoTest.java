import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todoBody_nonEmptyBody_success() throws ChatbotException {
        assertEquals(Todo.newTodo("Read book").getDescription(), "Read book");
    }

    @Test
    public void todoBody_emptyBody_exceptionThrown(){
        try {
            Todo.newTodo("");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), "Ooopsss (>.>) Todo task cannot be empty!!");
        }
    }
}
