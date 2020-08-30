package ultron.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void getTypeTest() {
        assertEquals("DEADLINE", new Deadline("deadline", "12pm").getType());
    }

    @Test
    public void getDateTestString() {
        assertEquals("12pm", new Deadline("hello", "12pm").getDate());
    }

    @Test
    public void getDateTestDate() {
        assertEquals("02-02-2002 1800", new Deadline("hello", "02-02-2002 1800").getDate());
    }

    @Test
    public void getCommandTestString() {
        assertEquals("hello /by 12pm", new Deadline("hello", "12pm").getCommand());
    }

    @Test
    public void getCommandTestDate() {
        assertEquals("hello /by 02-02-2002 1800", new Deadline("hello", "02-02-2002 1800").getCommand());
    }
    /**
     * Test Parsing functionality of deadline class.
     */
    @Test
    public void parseCommandTest() {
        Task deadline = Deadline.parseCommand("hello /by 02-02-2002 1800");
        assertEquals("hello", deadline.getMessage());
        assertEquals("DEADLINE", deadline.getType());
        assertEquals("\u2718", deadline.getStatusIcon());
        assertEquals("[D][\u2718] hello (by: 02-02-2002 1800)", deadline.toString());
    }

}
