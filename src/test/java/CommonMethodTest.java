import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.CommonMethod;

public class CommonMethodTest {

    @Test
    public void testMergeArray() {
        String[] array = {"Hello", "World!"};
        assertEquals("Hello World!", CommonMethod.mergeArray(array, 0, 2));
    }

    @Test
    public void testIsLeapYear() {
        assertFalse(CommonMethod.isLeapYear(1981)); // not a multiple of 4
        assertTrue(CommonMethod.isLeapYear(2012)); // multiple of 4, not divisible by 100, leap
        assertFalse(CommonMethod.isLeapYear(1700)); // multiple of 100, not divisible by 400, not leap
        assertTrue(CommonMethod.isLeapYear(1600)); // multiple of 100, divisible by 400, leap
    }
}
