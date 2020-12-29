package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDataException;
import duke.exception.InvalidTypeException;

public class StorageTest {
    private Storage s = new Storage();

    @Test
    public void convertStringToTask_invalidData_invalidDataException() {
        try {
            s.stringToTask("example hello");
        } catch (InvalidTypeException e) {
            fail();
        } catch (InvalidDataException e) {
            assertEquals("OOPS! The data here is invalid! Initialising with empty array.",
                    e.getMessage());
        }
    }

    @Test
    public void convertStringToTask_invalidType_invalidTypeException() {
        try {
            s.stringToTask("notype|1|example|example");
        } catch (InvalidTypeException e) {
            assertEquals("OOPS! Command was not executed! "
                            + "Please use a valid command. Type 'help' for more information.",
                    e.getMessage());
        } catch (InvalidDataException e) {
            fail();
        }
    }
}
