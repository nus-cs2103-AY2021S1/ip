package chatbot.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventBody_datePresent_success() {
        Event evt = Event.newEvent("meeting", LocalDate.parse("2020-08-25"));
        assertEquals(evt.getDescription(), "meeting");
        assertEquals(evt.getDate().toString(), "2020-08-25");
    }
}
