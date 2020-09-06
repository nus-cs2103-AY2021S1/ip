package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void getPrettyErrorMsg_dukeException_correctOutput() {
        try {
            throw new DukeException("test");
        } catch (DukeException e) {
            assertEquals("[ERROR]: test", e.getPrettyErrorMsg());
        }
    }
}
