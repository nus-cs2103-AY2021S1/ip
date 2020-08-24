import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void toString_creatingTodo() {
        String expected = "[T][✘] hehe";
        try {
            String actual = new Todo("hehe").toString();
            assertEquals(expected, actual);
        } catch (EmptyBodyException e) {
            fail("Expected: " + expected + "\n Actual:" + e.toString());
        }
    }

    @Test
    public void toString_creatingTodoWithEmptyDescription_exceptionThrown() {
        String expectedMessage = ":((( OOPS!!! The description of a class Todo cannot be empty.";

        EmptyBodyException e = assertThrows(EmptyBodyException.class, () -> new Todo(""));
        assertEquals(expectedMessage, e.getMessage());

    }
}
