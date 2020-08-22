package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void testGetSize() {
        assertEquals(0, taskList.getSize());
    }
}
