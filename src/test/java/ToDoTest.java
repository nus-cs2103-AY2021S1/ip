import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString(){
        assertEquals("[T]" + "[" + "\u2718" + "]" + " read book", new Todo("read book").toString());
    }

    @Test
    public void testSaveString(){
        assertEquals("T , 0 , read book", new Todo("read book").saveString());
    }
}
