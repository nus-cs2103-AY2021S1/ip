package tasks;

import main.java.exceptions.InvalidDescriptionException;
import main.java.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void todo_emptyTodoDescription_exceptionThrown() {
        try {
            new Todo("");
        } catch (InvalidDescriptionException e) {
            assertEquals("Hey! " +
                    "Todo description shouldn't be blank.", e.getMessage());
        }
    }

    @Test
    public void todo_blankTodoDescription_exceptionThrown() {
        try {
            new Todo(" ");
        } catch (InvalidDescriptionException e) {
            assertEquals("Hey! " +
                    "Todo description shouldn't be blank.", e.getMessage());
        }
    }

    @Test
    public void todo_normalTodoDescription_success() {
        try {
            new Todo("learn how to skate");
        } catch (InvalidDescriptionException e) {
            fail();
        }
    }
}
