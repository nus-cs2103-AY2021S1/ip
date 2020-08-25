package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnknownCommandExceptionTest {

    @Test
    public void test_UnknownCommandException_Message() {
        try {
            throw new UnknownCommandException("test");
        } catch (UnknownCommandException e) {
            assertEquals("test", e.getMessage());
        }
    }
}