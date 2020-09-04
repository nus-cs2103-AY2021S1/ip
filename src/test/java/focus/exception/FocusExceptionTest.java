package focus.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FocusExceptionTest {
    @Test
    public void testFocusException() {
        assertEquals("This is an error.",
                new FocusException("This is an error.").getMessage());
        assertEquals("This is also an error.",
                new FocusException("This is also an error.").getMessage());
    }
}
