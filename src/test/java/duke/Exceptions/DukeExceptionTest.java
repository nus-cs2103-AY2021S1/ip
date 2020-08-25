package duke.Exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DukeExceptionTest {
    @Test
    public void getMessageTest1() {
        try {
            throw new DukeException("test 1");
        } catch (DukeException e) {
            assertEquals("test 1", e.getMessage());
        }
    }

    @Test
    public void getMessageTest2() {
        try {
            throw new DukeException("test 2");
        } catch (Exception e) {
            assertEquals("test 2", e.getMessage());
        }
    }

    @Test
    public void getMessageTest3() {
        try {
            throw new DukeException("test 1");
        } catch (Exception e) {
            assertTrue(e instanceof DukeException);
        }
    }
}
