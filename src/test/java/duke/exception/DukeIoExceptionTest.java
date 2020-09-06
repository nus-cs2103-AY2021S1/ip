package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeIoExceptionTest {

    @Test
    public void getPrettyErrorMsg_dukeIoException_correctOutput() {
        try {
            throw new DukeIoException("test");
        } catch (DukeException e) {
            assertEquals("[IO ERROR]: test", e.getPrettyErrorMsg());
        }
    }
}
