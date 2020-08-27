import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void numTasksTest1(){
        List<Task> lst = new ArrayList<>();
        lst.add(new Task("test"));
        TaskList testList = new TaskList(lst);
        
        String expected = "1 task";
        String result = testList.numTasks();
        assertEquals(expected, result);
    }

    @Test
    public void numTasksTest2(){
        List<Task> lst = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            lst.add(new Task("test"));
        }
        TaskList testList = new TaskList(lst);

        String expected = "3 tasks";
        String result = testList.numTasks();
        assertEquals(expected, result);
    }
}