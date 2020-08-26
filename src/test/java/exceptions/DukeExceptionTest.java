package exceptions;

import main.java.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void testGetMessage() {
        assertEquals("message", new DukeException("message").getMessage());
    }

}