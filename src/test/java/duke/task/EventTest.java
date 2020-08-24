package duke.task;

import duke.exception.WrongFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void newEvent_validInput_eventObject() {
        try {
            new Event("meeting", "NUS");
            assertTrue(true);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void newEvent_invalidInputNoDescription_wrongFormatExceptionThrown() {
        try {
            new Event("", "NUS");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void newEvent_invalidInputNoVenue_eventObject() {
        try {
            new Event("meeting", "");
            assertTrue(true);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void newEvent_invalidInputNoDescriptionNorVenue_wrongFormatExceptionThrown() {
        try {
            new Event("", "");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }
}