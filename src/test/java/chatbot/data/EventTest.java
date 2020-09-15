package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    private final Event event = Event.newEvent("meeting", LocalDate.parse("2020-08-25"));

    @Test
    public void factoryMethod_validArguments_correctAttributes() {
        assertEquals(event.getDescription(), "meeting");
        assertEquals(event.getDate().toString(), "2020-08-25");
        assertEquals(event.getStatus(), "0");
        assertEquals(event.getType(), "E");
    }

    @Test
    public void markDone_correctStatus() {
        assertEquals(event.markDone().getStatus(), "1");
    }
}
