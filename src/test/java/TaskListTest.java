
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents Task list Test class and consists of methods to test the Task list.
 */
public class TaskListTest {

    /**
     * Whether the size of the List is the same.
     */
    @Test
    void getSizeOfListUiTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSizeOfList(), 0);
    }
}
