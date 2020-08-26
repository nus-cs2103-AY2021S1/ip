import duke.Event;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getDescriptionTest(){
        assertEquals(Event.getDescription("event abcd /at 1111-11-11 11:11"), "abcd");
    }

    @Test
    public void getTimeTest(){
        assertEquals(Event.getTime("event abcd /at 1111-11-11 11:11"), "1111-11-11 11:11");
    }

    @Test
    public void changeDateFormatTest(){
        String[] s = {"/at", "1111/11/11"};
        assertEquals(Event.changeDateFormat(s), "1111-11-11");
    }
}