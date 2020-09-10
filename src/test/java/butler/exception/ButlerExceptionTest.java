package butler.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ButlerExceptionTest {

    @Test
    public void getMessageTest() {
        ButlerException e = new ButlerException("Test Message");
        assertEquals("Test Message", e.getMessage());
    }
}
