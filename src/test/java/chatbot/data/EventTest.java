package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void factoryMethod_validArguments_correctAttributes() {
        Event e = Event.newEvent("meeting", LocalDate.parse("2020-08-25"));
        assertEquals(e.getDescription(), "meeting");
        assertEquals(e.getDate().toString(), "2020-08-25");
        assertEquals(e.getStatus(), "0");
        assertEquals(e.getType(), "E");
    }
}
