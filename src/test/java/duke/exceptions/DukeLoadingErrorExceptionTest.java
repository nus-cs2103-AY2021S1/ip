package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeLoadingErrorExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_LOADING_ERROR, new DukeLoadingErrorException().toString());
    }

}
