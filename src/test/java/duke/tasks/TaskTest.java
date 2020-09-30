package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testGetDescription() {
        assertEquals ("Sleep", new Task("Sleep", "Todo", false).getDescription());
        assertEquals ("Eat", new Task ("Eat", "Todo", false).getDescription());
    }

    /**
     * Tests getDone() method in Task class
     */
    @Test
    public void testGetDone() {
        assertEquals(true, new Task ("Ice skate", "Todo", true).getDone());
        assertEquals(false, new Task ("Ice skate", "Todo", false).getDone());
    }
}
