package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;

public class EventTest {
    @Test
    public void factoryMethod_invalidDate_exceptionThrown() {
        try {
            assertNull(Event.create("test /at 2 9 12"));
            fail();
        } catch (InvalidDateException e) {
            assertEquals("Invalid date format detected!\n"
                    + "Accepted formats: 'dd/MM/yyyy' or 'dd-MM-yyyy", e.toString());
        } catch (MissingDateException e) {
            e.printStackTrace();
            fail();
        }
    }
}
