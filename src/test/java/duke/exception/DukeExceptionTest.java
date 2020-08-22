package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void testDukeException() {
        assertEquals("This is an error.",
                new DukeException("This is an error.").getMessage());
        assertEquals("This is also an error.",
                new DukeException("This is also an error.").getMessage());
    }
}
