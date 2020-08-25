package duke.task;

import duke.exception.InvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void factoryMethod_invalidDate_exceptionThrown() throws Exception {
        try {
            assertNull(Event.create("event test /at 2 9 12"));
            fail();
        } catch (InvalidDateException e) {
            assertEquals("Invalid date format detected!\n"
                    + "Accepted formats: 'dd/MM/yyyy' or 'dd-MM-yyyy", e.toString());
        }
    }
}
