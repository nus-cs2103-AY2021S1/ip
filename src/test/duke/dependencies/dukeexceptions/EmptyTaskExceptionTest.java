package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyTaskExceptionTest {

    @Test
    public void test_EmptyTaskException_Message() {
        try {
            throw new EmptyTaskException("test");
        } catch (EmptyTaskException e) {
            assertEquals("test", e.getMessage());
        }
    }

}