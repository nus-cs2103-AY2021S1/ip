import dude.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dude.task.Event;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void eventTest_creation_success(){
        Event event = new Event("test", true, LocalDate.parse("2020-10-12"));
        assertEquals("[E][✓] test (date: Oct 12 2020)", event.toString());
    }
}
