package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void testGetMessage() {
        assertEquals("message", new DukeException("message").getMessage());
    }

}
