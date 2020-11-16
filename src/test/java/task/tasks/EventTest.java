package task.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void correctEventRepresentation(){
        Event event = new Event("Sample Event", "Mon 2 - 4pm");
        assertEquals("[E][N] Sample Event (at: Mon 2 - 4pm)", event.toString());
        assertEquals("N", event.getStatusIcon());
    }

    @Test
    public void testDone(){
        Event event = new Event("Sample Event", "Mon 2 - 4pm");
        event.markAsDone();
        assertEquals("[E][Y] Sample Event (at: Mon 2 - 4pm)", event.toString());
        assertEquals("Y", event.getStatusIcon());
    }
}
