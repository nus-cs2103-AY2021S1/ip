package junimo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    public void constructor() {
        // Test with correct arguments
        Event event1 = new Event("Eat Apple", "NUS", false);
        Event event2 = new Event("Write paper", "Matt's House", true);

        assertEquals("Eat Apple", event1.description);
        assertEquals("NUS", event1.at);
        assertEquals(false, event1.isDone);
        assertEquals("Write paper", event2.description);
        assertEquals("Matt's House", event2.at);
        assertEquals(true, event2.isDone);
    }

    @Test
    public void getParsedTask() {
        Event event = new Event("Eat Apple", "NUS", true);
        String expectedParsedTask = "event Eat Apple /at NUS" + System.lineSeparator()
                + "true" + System.lineSeparator();
        assertEquals(expectedParsedTask, event.getParsedTask());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("Eat Apple", "NUS", true);
        String expectedToString = "[E][\u2713] Eat Apple (at: NUS)";
        assertEquals(expectedToString, event.toString());
    }

    @Test
    public void equals() {
        Event event1 = new Event("Eat Apple", "NUS", true);
        Event event2 = new Event("Eat Apple", "NUS", true);
        assertTrue(event1.equals(event2));
    }
}
