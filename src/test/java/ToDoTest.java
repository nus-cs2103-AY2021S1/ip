import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.ToDo;

public class ToDoTest {
    @Test
    public void constructorTest() {
        ToDo events = new ToDo("kiwis");
        assertEquals(
                "[T][" + "\u2718" + "] kiwis", events.toString());
    }

    @Test
    public void completionTest() {
        ToDo events = new ToDo("kiwis");
        events.completeTask();
        assertEquals("[T][" + "\u2713" + "] kiwis", events.toString());
    }
}
