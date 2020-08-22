package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeIOExceptionTest {
    @Test
    public void getPrettyErrorMsg_dukeIOException_correctOutput() {
        try {
            throw new DukeIOException("test");
        } catch (DukeException e) {
            assertEquals(e.getPrettyErrorMsg(), "[IO ERROR]: test");
        }
    }
}