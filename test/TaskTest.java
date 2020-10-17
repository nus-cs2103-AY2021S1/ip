import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon() {
        assertEquals("✘", new ToDo("todo borrow book").getStatusIcon());
        assertEquals("✘", new Event("event project meeting", "2-4pm").getStatusIcon());
    }

    @Test
    void getDescription() {
        assertEquals("todo borrow book", new ToDo("todo borrow book").getDescription());
        assertEquals("event project meeting", new Event("event project meeting", "2-4pm")
                .getDescription());
    }

    @Test
    void testToString() {
        assertEquals("[T][✘] todo borrow book", new ToDo("todo borrow book").toString());
        assertEquals("[E][✘] event project meeting (at: 2-4pm)", new Event("event project meeting", "2-4pm")
                .toString());
    }
}