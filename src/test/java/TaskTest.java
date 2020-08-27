import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    
    Task task = new Task("return book");
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
    
    @Test
    public void testGetDescription() {
        assertEquals("return book", task.getDescription());
    }
    
    @Test
    public void testIsDone() {
        assertEquals(false, task.isDone());
    }
}
