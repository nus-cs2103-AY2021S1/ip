import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    
    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] borrow book",
            new Task("borrow book", "T").toString());
    }

    @Test
    public void getDescription_someString_success() {
        assertEquals("buy calculator", 
            new Task("buy calculator", "T")
            .getDescription());
    }

    @Test 
    public void getType_ofTypeT_success() {
        assertEquals("T", 
            new Task("someTask", "T")
            .getType());
    }
}