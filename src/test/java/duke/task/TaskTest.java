package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    Task task = new Task("t");

    @Test
    public void testComplete() {
        task.complete();
        assertTrue(task.isDone);
    }

    @Test
    public void test() {
        String str = task.store();
        assertEquals("false|t", str);
    }

}
