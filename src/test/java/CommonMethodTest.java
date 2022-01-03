import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.CommonMethod;

public class CommonMethodTest {
    @Test
    public void isLeapYearTest() {
        assertFalse(CommonMethod.isLeapYear(2017));
        assertTrue(CommonMethod.isLeapYear(2016));
        assertTrue(CommonMethod.isLeapYear(2000));
        assertFalse(CommonMethod.isLeapYear(2100));
    }

    @Test
    public void mergeArrayTest() {
        String[] array = {"Hello", "World"};
        assertEquals(CommonMethod.mergeArray(array, 0, 2), "Hello World");
    }
}
