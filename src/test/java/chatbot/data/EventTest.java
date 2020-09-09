package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void eventBody_datePresent_success() {
        Event evt = Event.newEvent("meeting", LocalDate.parse("2020-08-25"));
        assertEquals(evt.getDescription(), "meeting");
        assertEquals(evt.getDate().toString(), "2020-08-25");
    }
}
