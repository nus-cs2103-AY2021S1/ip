package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDataException;
import duke.exception.InvalidTypeException;

public class StorageTest {
    private Storage s = new Storage();

    @Test
    public void invalidDataTest() {
        try {
            s.stringToTask("example hello");
        } catch (InvalidTypeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        } catch (InvalidDataException e) {
            assertEquals("OOPS!!! The data here is invalid!",
                    e.getMessage());
        }
    }
}
