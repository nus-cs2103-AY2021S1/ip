package main.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InvalidDateExceptionTest {
    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should create exception instance with message")
        public void constructor_message_exception() {
            String message = "test";
            assertEquals(message, new InvalidDateException(message).getMessage());
        }

        @Test
        @DisplayName("should create exception instance with alt message")
        public void constructor_altMessage_exception() {
            String message = "different message";
            assertEquals(message, new InvalidDateException(message).getMessage());
        }
    }
}
