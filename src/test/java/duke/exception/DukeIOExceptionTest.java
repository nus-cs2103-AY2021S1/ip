package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeIOExceptionTest {

    @Test
    public void getPrettyErrorMsg_dukeIOException_correctOutput() {
        try {
            throw new DukeIOException("test");
        } catch (DukeException e) {
            assertEquals("[IO ERROR]: test", e.getPrettyErrorMsg());
        }
    }
}