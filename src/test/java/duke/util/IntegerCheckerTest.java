package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerCheckerTest {

    @Test
    @DisplayName("Check if value is not a number")
    public void checkIfNotANumberTest() {
        IntegerChecker integerChecker = new IntegerChecker();
        assertEquals(false, integerChecker.isNumber("b"));
    }

    @Test
    @DisplayName("Check if value is a number")
    public void checkIfNumberTest() {
        IntegerChecker integerChecker = new IntegerChecker();
        assertEquals(true, integerChecker.isNumber("2"));
    }
}
