package main.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InvalidTaskExceptionTest {
    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should create exception instance with default message")
        public void constructor_message_exception() {
            assertEquals("Your selected task does not exist!",
                    new InvalidTaskException().getMessage());
        }
    }
}
