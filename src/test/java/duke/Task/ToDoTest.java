package duke.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void TestConstructor() {
        assertEquals("[T][\u2717] Test1", new ToDo("Test1").toString());
    }

    @Test
    public void TestCompletedTask() {
        ToDo testee = new ToDo("Test2");
        testee.completeTask();
        assertEquals("[T][\u2713] Test2", testee.toString());
    }
}