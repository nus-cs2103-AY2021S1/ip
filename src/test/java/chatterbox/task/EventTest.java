package chatterbox.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void checkEventFormatting() {
        Event e = new Event("borrow books /at 20/1/2019 1800");
        assertEquals(e.inputString, "event borrow books /at 20/1/2019 1800");
        assertEquals(e.toString(), "[E][✗] borrow books (at: Jan 20 2019 1800H)");
        e.setDone(true);
        assertEquals(e.toString(), "[E][✓] borrow books (at: Jan 20 2019 1800H)");
    }
}
