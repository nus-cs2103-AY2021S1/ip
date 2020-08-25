package duke.task;

import duke.exception.EmptyTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void factoryMethod_success() throws EmptyTaskException {
        assertEquals(new ToDo("test event"), ToDo.create("todo test event"));
    }

    @Test
    public void factoryMethod_invalidCommand_ExceptionThrown() {
        try {
            assertNull(ToDo.create("todo "));
            fail();
        } catch (EmptyTaskException e) {
            assertEquals("You can't add a non-existent todo, silly!", e.toString());
        }
    }
}
