package test.java;

import main.java.duke.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getSizeTest(){
        assertEquals(new TaskList().getSize(), 0);
    }
}
