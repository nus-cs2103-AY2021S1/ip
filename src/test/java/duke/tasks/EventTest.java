package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void invalidDate_exceptionThrown() {
        Event event = new Event("hi", "2020-10-19");
        try {
            event.hasDate("202020");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, Invalid date.");
        }
    }
}
