package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoTaskToString_completeTaskCorrectly() {
        ToDo task = new ToDo("join sports club");
        assertEquals("[T][✘] join sports club", task.toString());
        task.completeTask();
        assertEquals("[T][✓] join sports club", task.toString());
    }
}
