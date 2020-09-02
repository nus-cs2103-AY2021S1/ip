package tasks;

import exceptions.InvalidDescriptionException;
import exceptions.InvalidTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void event_normalEventDescriptionAndTime_success() {
        try {
            new Event("chill with family vacation", "next Monday");
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void event_blankEventDescription_exceptionThrow() {
        try {
            new Event(" ", "next Monday");
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            assertEquals("Hey! " +
                    "Event description shouldn't be blank.", e.getMessage());
        }
    }
}
