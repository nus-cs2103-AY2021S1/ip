import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    
    Task task = new Task("return book");
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    /**
     * Tests the getDescription method from the Task Class
     */
    @Test
    public void testGetDescription() {
        assertEquals("return book", task.getDescription());
    }

    /**
     * Tests the testIsDone method from the Task Class
     */
    @Test
    public void testIsDone() {
        assertEquals(false, task.isDone());
    }
}
