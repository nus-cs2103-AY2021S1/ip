package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void taskToArray_toDo_test() {
        Todo todo = new Todo("Return book");
        String[] expectedArr = new String[]{"T", "1", "Return book"};
        assertArrayEquals(expectedArr, todo.taskToArray());
    }



}
