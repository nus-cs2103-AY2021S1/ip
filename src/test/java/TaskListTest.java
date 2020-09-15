
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void getSizeOfListUiTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSizeOfList(), 0);
    }
}
