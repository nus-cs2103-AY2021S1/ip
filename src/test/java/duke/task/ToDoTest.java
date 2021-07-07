package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void toString_normalInput_correctOutput() {
        ToDo todo = new ToDo("todo1", false);
        assertEquals("[T][\u2718] todo1", todo.toString());
    }

    @Test
    void getDataString_noInput_correctOutput() {
        ToDo todo = new ToDo("todo1", false);
        String[] expected = new String[] {"todo", "false", "todo1"};
        String[] actual = todo.getDataStrings();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
