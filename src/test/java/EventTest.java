import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dude.task.Event;

public class EventTest {
    @Test
    public void eventTest_creation_success() {
        Event event = new Event("test", true, LocalDate.parse("2020-10-12"));
        assertEquals("[E][\u2713] test (date: Oct 12 2020)", event.toString());
    }
}
