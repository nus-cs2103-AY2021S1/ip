package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeFileNotFoundExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_FILE_NOT_FOUND, new DukeFileNotFoundException().toString());
    }

}
