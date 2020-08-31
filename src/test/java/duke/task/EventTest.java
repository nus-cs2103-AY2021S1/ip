package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void create_validEvent_test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date);
        assertEquals("[E][✘] iP Week 3 (at: August 27 2020)", e.toString());
    }

    @Test
    void encode_incompleteEvent_test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date);
        assertEquals(e.getId() + " | E | 0 | iP Week 3 | 2020-08-27", e.encode());
    }

    @Test
    void encode_completedEvent_test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event e = new Event("iP Week 3", date, "1");
        assertEquals(e.getId() + " | E | 1 | iP Week 3 | 2020-08-27", e.encode());
    }
}
