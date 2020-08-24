import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    Deadline deadline = new Deadline("read books", "2020-08-25");
    @Test
    public void testToString() {
        assertEquals("[D][✘] read books (by: Aug 25 2020)", deadline.toString());
    }
    
    @Test 
    public void testGetData() {
        assertEquals("d/0/read books/2020-08-25", deadline.getData());
    }
}