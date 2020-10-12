import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.Event;



public class EventTest {
    @Test
    public void testEventCreator() {
        Event eventToTest = Event.createEvent("test /at 5/05/2019");
        String expectedOutcome = "[E][\u2718] test (at: 5 May 2019)";
        assertEquals(expectedOutcome, eventToTest.toString());
    }
}
