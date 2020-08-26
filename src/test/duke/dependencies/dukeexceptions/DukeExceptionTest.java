package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeExceptionTest {

    @Test
    public void test_DukeException_Message() {
        try {
            throw new DukeException("test");
        } catch (DukeException e) {
            assertEquals("test", e.getMessage());
        }
    }

}