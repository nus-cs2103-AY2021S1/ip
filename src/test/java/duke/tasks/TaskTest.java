package duke.tasks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    
    @Test
    public void testGetDescription() {
        assertEquals ("Sleep", new Task("Sleep", "Todo").getDescription());
    }
    
    @Test
    public void testGetDone() {
        assertEquals(true, new Task ("Ice skate", "Todo", true).getDone());
        assertEquals(false, new Task ("Ice skate", "Todo").getDone());
    }
}
