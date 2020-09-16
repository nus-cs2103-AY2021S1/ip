import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        assertEquals("[D]" + "[" + "\u2718" + "]" + " read book (by: Oct 12 2020)", 
                new Deadline("read book", LocalDate.parse("2020-10-12")).toString());
    }

    @Test
    public void testSaveString(){
        assertEquals("D , 0 , read book , 2020-10-12",
                new Deadline("read book", LocalDate.parse("2020-10-12")).saveString());
    }
}
