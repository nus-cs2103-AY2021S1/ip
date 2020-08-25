package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EventTest {
    @Test
    void create_Event_correctly() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date);
        assertEquals("[E][✘] iP Week 3 (at: August 27 2020)", e.toString());
    }

    @Test
    void encode_incompleteEvent_Test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date);
        assertEquals("E | 0 | iP Week 3 | 2020-08-27", e.encode());
    }

    @Test
    void encode_completedToDo_Test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date, "1");
        assertEquals("E | 1 | iP Week 3 | 2020-08-27", e.encode());
    }
}
