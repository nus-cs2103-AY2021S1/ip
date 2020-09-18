package sparrow.data.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    private Event event = new Event("I am an event.", LocalDate.now());

    @Test
    public void equals() {
        Event sameEvent = new Event("I am an event.", LocalDate.now());
        Event diffDescription = new Event("I am another event.", LocalDate.now());
        Event diffDate = new Event("I am an event.", LocalDate.EPOCH);

        // same details -> returns true
        assertTrue(event.equals(sameEvent));

        // different description -> returns false
        assertFalse(event.equals(diffDescription));

        // different date -> returns false
        assertFalse(event.equals(diffDate));

        // null -> returns false
        assertFalse(event.equals(null));

        // different type -> returns false
        assertFalse(event.equals(27));
    }
}
