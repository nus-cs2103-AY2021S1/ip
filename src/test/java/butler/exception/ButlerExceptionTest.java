package butler.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButlerExceptionTest {

    @Test
    public void getMessageTest() {
        ButlerException e = new ButlerException("Test Message");
        assertEquals("Test Message", e.getMessage());
    }
}
