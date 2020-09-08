import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ValidatorTest {

    @Test
    public void requireNonNull_passNullIn_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> Validator.requireNonNull(null, null, null));
        assertThrows(NullPointerException.class, () -> Validator.requireNonNull(1, 2, 3, null));
    }

    @Test
    public void requireNonNull_passObjectsIn() {
        assertDoesNotThrow(() -> Validator.requireNonNull(1, 2, 3));
        assertDoesNotThrow(() -> Validator.requireNonNull("hehe"));
        assertDoesNotThrow(() -> Validator.requireNonNull("Pikachu", "says", "hello"));
    }

}