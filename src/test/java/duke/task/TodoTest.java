package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void taskToArray_ToDo_Test() {
        Todo todo = new Todo("Return book");
        String[] expected_arr = new String[]{"T", "1", "Return book"};
        assertArrayEquals(expected_arr, todo.taskToArray());
    }



}
