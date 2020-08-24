import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetStatusIcon(){
        assertEquals("\u2718", new Task("read book").getStatusIcon());
    }

    @Test
    public void testDoneState(){
        assertEquals(0, new Task("read book").doneState());
    }
    
    @Test
    public void testToString(){
        assertEquals("[" + "\u2718" + "]" + " read book", new Task("read book").toString());
    }
    
    @Test
    public void testSaveString(){
        assertEquals(" , 0 , read book", new Task("read book").saveString());
    }
    
}
