package main.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EmptyMessageExceptionTest {
    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should create exception instance with todo command")
        public void constructor_todoCommand_exception() {
            String command = "todo";
            assertEquals("The description of a todo cannot be empty.",
                    new EmptyMessageException(command).getMessage());
        }

        @Test
        @DisplayName("should create exception instance with event command")
        public void constructor_eventCommand_exception() {
            String command = "event";
            assertEquals("The description of a event cannot be empty.",
                    new EmptyMessageException(command).getMessage());
        }

        @Test
        @DisplayName("should create exception instance with deadline command")
        public void constructor_deadlineCommand_exception() {
            String command = "deadline";
            assertEquals("The description of a deadline cannot be empty.",
                    new EmptyMessageException(command).getMessage());
        }
    }
}
