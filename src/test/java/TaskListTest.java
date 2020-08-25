import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    void getSizeOfList_Ui_Test() {
        TaskList taskList = new TaskList();

        assertEquals(taskList.getSizeOfList(),0 );
    }
}
