package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testConstructor() {
        assertEquals("[T][\u2717] Test1", new ToDo("Test1").toString());
    }

    @Test
    public void testCompletedTask() {
        ToDo testee = new ToDo("Test2");
        testee.completeTask();
        assertEquals("[T][\u2713] Test2", testee.toString());
    }
}
