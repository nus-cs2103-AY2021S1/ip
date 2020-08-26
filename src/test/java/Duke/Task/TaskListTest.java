package Duke.Task;

import Duke.Tool.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    
    @Test
    public void getCountTest() {
        ArrayList<Task> taskList = new ArrayList<>(100);
        TaskList tl = new TaskList(taskList);
        String actualOutput = tl.countNum();
        String expected = "    Now you have 0 tasks in the list.";
        assertEquals(expected, actualOutput);
    }
    
}
