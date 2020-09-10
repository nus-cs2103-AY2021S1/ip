package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerCheckerTest {
    @Test
    @DisplayName("Check if input is not a number")
    public void checkIfNotANumber() {
        IntegerChecker integerChecker = new IntegerChecker();
        assertEquals(false, integerChecker.isNumber("b"));
    }

    @Test
    @DisplayName("Check if input is number")
    public void checkIfNumber() {
        IntegerChecker integerChecker = new IntegerChecker();
        assertEquals(true, integerChecker.isNumber("2"));
    }
}
