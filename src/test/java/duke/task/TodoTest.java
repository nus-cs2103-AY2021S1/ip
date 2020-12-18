package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void encode_incompleteToDo_test() {
        ToDo todo = new ToDo("Reading");
        assertEquals(todo.getId() + " | T | 0 | Reading", todo.encode());
    }

    @Test
    void encode_completedToDo_test() {
        ToDo todo = new ToDo("Reading", "1");
        assertEquals(todo.getId() + " | T | 1 | Reading", todo.encode());
    }
}
