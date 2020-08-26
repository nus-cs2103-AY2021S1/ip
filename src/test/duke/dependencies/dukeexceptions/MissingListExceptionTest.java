package duke.dependencies.dukeexceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingListExceptionTest {

    @Test
    public void test_MissingListException_Message() {
        try {
            throw new MissingListException("test");
        } catch (MissingListException e) {
            assertEquals("test", e.getMessage());
        }
    }

}