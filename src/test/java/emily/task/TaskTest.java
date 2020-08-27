package emily.task;

import main.java.emily.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void testIsFinished(){
        assertEquals(false, new Task("").isFinished());
    }
}