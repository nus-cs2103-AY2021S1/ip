package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnspecifiedDateExceptionTest {

    @Test
    public void test_UnspecifiedDateException_Message() {
        try {
            throw new UnspecifiedDateException("test");
        } catch (UnspecifiedDateException e) {
            assertEquals("test", e.getMessage());
        }
    }

}