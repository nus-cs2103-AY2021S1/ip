import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.ToDos;

public class ToDosTest {
    @Test
    public void constructorTest() {
        ToDos events = new ToDos("kiwis");
        assertEquals(
                "[T][" + "\u2718" + "] kiwis", events.toString());
    }

    @Test
    public void completionTest() {
        ToDos events = new ToDos("kiwis");
        events.completeTask();
        assertEquals("[T][" + "\u2713" + "] kiwis", events.toString());
    }
}
