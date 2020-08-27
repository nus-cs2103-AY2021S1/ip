import duke.CommonMethod;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonMethodTest {

    @Test
    public void testMergeArray() {
        String[] array = {"Hello", "World!"};
        assertEquals("Hello World!", CommonMethod.mergeArray(array, 0, 2));
    }

    @Test
    public void testIsLeapYear() {
        assertEquals(false, CommonMethod.isLeapYear(1981)); // not a multiple of 4
        assertEquals(true, CommonMethod.isLeapYear(2012));// multiple of 4, not divisible by 100, leap
        assertEquals(false, CommonMethod.isLeapYear(1700)); // multiple of 100, not divisible by 400, not leap
        assertEquals(true, CommonMethod.isLeapYear(1600)); // multiple of 100, divisible by 400, leap
    }
}
