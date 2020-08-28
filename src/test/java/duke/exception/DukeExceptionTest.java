package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void testDukeException() {
        assertEquals("This is an error.",
                new DukeException("This is an error.").getMessage());
        assertEquals("This is also an error.",
                new DukeException("This is also an error.").getMessage());
    }
}
