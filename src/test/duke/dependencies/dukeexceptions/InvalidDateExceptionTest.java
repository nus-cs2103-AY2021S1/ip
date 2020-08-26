package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidDateExceptionTest {

    @Test
    public void test_InvalidDateException_Message() {
        try {
            throw new InvalidDateException("test");
        } catch (InvalidDateException e) {
            assertEquals("test", e.getMessage());
        }
    }

}