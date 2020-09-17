package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeUnknownInputExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_UNKNOWN_INPUT, new DukeUnknownInputException().toString());
    }

}
