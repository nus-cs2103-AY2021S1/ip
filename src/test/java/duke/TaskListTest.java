package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void size_getIntSize_success() {
        ArrayList<Task> aList = new ArrayList<>();
        TaskList taskList = new TaskList(aList);
        taskList.getTasks().add(new Task("swimming"));
        taskList.getTasks().add(new Task("read book"));
        assertEquals(2, taskList.size());
    }
}
