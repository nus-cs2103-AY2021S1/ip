package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_twoParam_success() {
        Event newEvent = new Event("workout", "2-4pm");
        assertFalse(newEvent.isDone());
        assertEquals("[E][\u2718] workout (at: 2-4pm)", newEvent.toString());

    }

    @Test
    public void constructor_fullParams_success() {
        Event newEvent = new Event("workout", true, "Park, Singapore", LocalDateTime.now());
        assertTrue(newEvent.isDone());
        assertEquals("[E][\u2713] workout (at: Park, Singapore)", newEvent.toString());
    }

    @Test
    public void getTimeFrame_correct_success() {
        Event newEvent = new Event("workout", true, "2pm", LocalDateTime.now());
        assertEquals("2pm", newEvent.getTimeFrame());
    }

    @Test
    public void getTime_defaultTime_success() {
        Event event = new Event("test", "in school");
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMM yyyy @ h.mm a")), event.getTime());
    }

}
