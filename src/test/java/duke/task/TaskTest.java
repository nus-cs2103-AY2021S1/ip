package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task = new ToDo("t");

    @Test
    public void testComplete() {
        task.complete();
        assertTrue(task.isDone);
    }

    @Test
    public void test() {
        String str = task.store();
        assertEquals("T|false|t", str);
    }

}
