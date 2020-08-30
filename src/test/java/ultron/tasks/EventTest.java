package ultron.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getTypeTest() {
        assertEquals("EVENT", new Event("event", "12pm").getType());
    }

    @Test
    public void getDateTestString() {
        assertEquals("12pm", new Event("hello", "12pm").getDate());
    }

    @Test
    public void getDateTestDate() {
        assertEquals("02-02-2002 1800", new Event("hello", "02-02-2002 1800").getDate());
    }

    @Test
    public void getCommandTestString() {
        assertEquals("hello /at 12pm", new Event("hello", "12pm").getCommand());
    }

    @Test
    public void getCommandTestDate() {
        assertEquals("hello /at 02-02-2002 1800", new Event("hello", "02-02-2002 1800").getCommand());
    }
    /**
     * Test Parsing functionality of deadline class.
     */
    @Test
    public void parseCommandTest() {
        Task deadline = Event.parseCommand("hello /at 02-02-2002 1800");
        assertEquals("hello", deadline.getMessage());
        assertEquals("EVENT", deadline.getType());
        assertEquals("\u2718", deadline.getStatusIcon());
        assertEquals("[E][\u2718] hello (at: 02-02-2002 1800)", deadline.toString());
    }
}
