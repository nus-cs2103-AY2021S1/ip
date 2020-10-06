package main.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InvalidOptionExceptionTest {
    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should create exception instance with default message")
        public void constructor_alias_exception() {
            assertEquals("Your option of test does not exist!",
                    new InvalidOptionException("test").getMessage());
        }

        @Test
        @DisplayName("should create exception instance with alternate alias")
        public void constructor_altAlias_exception() {
            assertEquals("Your option of alt does not exist!",
                    new InvalidOptionException("alt").getMessage());
        }
    }
}
