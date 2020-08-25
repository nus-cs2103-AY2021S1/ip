package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void encode_incompleteToDo_Test() {
        ToDo todo = new ToDo("Reading");
        assertEquals( "T | 0 | Reading", todo.encode());
    }

    @Test
    void encode_completedToDo_Test() {
        ToDo todo = new ToDo("Reading", "1");
        assertEquals("T | 1 | Reading", todo.encode());
    }
}
