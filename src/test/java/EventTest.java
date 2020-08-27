import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getType() {
        Event event = new Event("project meeting /at 2020-12-31", false, LocalDate.parse("2020-12-31"));
        assertEquals("E", event.getType());
    }
}