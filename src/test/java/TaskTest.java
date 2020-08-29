import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructorTest() {
        Task task = new Task("test name");
        Assertions.assertEquals("[✘] test name", task.toString());
    }

    @Test
    public void markDoneTest() {
        Task task = new Task("sample task");
        task.markDone();
        Assertions.assertEquals("[✓] sample task", task.toString());
    }

    @Test
    public void serializeTest() {
        Task task = new Task("another task");
        Assertions.assertEquals("0|another task", task.serialize());
    }

    @Test
    public void parseValidTest() {
        String serial = "T|1|valid task";
        Assertions.assertEquals("[T][✓] valid task", Task.parse(serial).toString());
    }

    @Test
    public void parseInvalidTest() {
        String serial = "K|0|invalid task";
        Assertions.assertEquals(null, Task.parse(serial));
    }
}
