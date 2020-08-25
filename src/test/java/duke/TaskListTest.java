package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testEmptyListDisplay() {
        assertEquals("Here are the tasks in your list: \n", 
                new TaskList().getAllTasksAsString());
    }
}
