package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;


public class DukeInvalidIndexExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_INVALID_INDEX, new DukeInvalidIndexException().toString());
    }

}
