package focus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void testGetSize() {
        assertEquals(0, taskList.getSize());
    }
}
