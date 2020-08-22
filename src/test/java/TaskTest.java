import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void constructorTest() {
        Task task = new Task("test name");
        assertEquals("[✘] test name", task.toString());
    }

    @Test
    public void markDoneTest() {
        Task task = new Task("sample task");
        task.markDone();
        assertEquals("[✓] sample task", task.toString());
    }

    @Test
    public void serializeTest() {
        Task task = new Task("another task");
        assertEquals("0|another task", task.serialize());
    }

    @Test
    public void parseValidTest() {
        String serial = "T|1|valid task";
        assertEquals("[T][✓] valid task", Task.parse(serial).toString());
    }

    @Test
    public void parseInvalidTest() {
        String serial = "K|0|invalid task";
        assertEquals(null, Task.parse(serial));
    }
}
