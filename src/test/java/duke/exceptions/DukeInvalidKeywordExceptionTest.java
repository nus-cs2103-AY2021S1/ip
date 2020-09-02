package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidKeywordExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_INVALID_KEYWORD, new DukeInvalidKeywordException().toString());
    }

}
