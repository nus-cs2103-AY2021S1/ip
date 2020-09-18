import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void numTasksTest1() {
        List<Task> lst = new ArrayList<>();
        lst.add(new Task("test"));
        TaskList testList = new TaskList(lst);

        String expected = "1 task";
        String result = testList.getNumTasksStr();
        assertEquals(expected, result);
    }

    @Test
    public void numTasksTest2() {
        List<Task> lst = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            lst.add(new Task("test"));
        }
        TaskList testList = new TaskList(lst);

        String expected = "3 tasks";
        String result = testList.getNumTasksStr();
        assertEquals(expected, result);
    }
}
