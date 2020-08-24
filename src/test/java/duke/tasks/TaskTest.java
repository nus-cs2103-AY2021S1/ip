package duke.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    
    @Test
    public void testGetDone() {
        assertEquals(false, new Task("Sleep", "Todo").getDone());
        assertEquals(true, new Task ("Sleep", "Todo", true).getDone());
    }
    
    @Test
    public void testGetDescription() {
        assertEquals("Buy Coffee", new Task ("Buy Coffee", "Todo").getDescription());
        assertEquals("Get Lost", new Task ("Get Lost", "hdfjfh").getDescription());
    }
}

