package task;

import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void event_toArray_test(){
        Event event = new Event("meeting", "2020-10-10");
        String[] expected = new String[]{"E", "0", "meeting", "2020-10-10"};
        assertArrayEquals(expected, event.toArray());
    }
}
