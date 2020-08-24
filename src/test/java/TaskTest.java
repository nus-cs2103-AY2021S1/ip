import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    
    
    @Test
    public void testToString() {
        Task task = new Task("read books");
        assertEquals("[✘] read books", task.toString());
    }
    
    @Test
    public void testSetDone() {
        Task task = new Task("read books");
        task.setDone();
        assertTrue(task.getStatus());
    }
    
    @Test
    public void testGetData() {
        Task task = new Task("read books");
        assertEquals("0/read books", task.getData());
    }
    
    @Test 
    public void testGetStatusIcon() {
        Task task = new Task("read books");
        assertEquals("✘", task.getStatusIcon());
    }
}
