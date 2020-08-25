import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testTimeFormat() {
        assertEquals(true, new Parser().isValidTime("00:00"));
    }

    @Test
    public void testTimeRange() {
        assertEquals(false, new Parser().isValidTime("99:99"));
    }

    @Test
    public void testDateFormat() {
        assertEquals(false, new Parser().isValidDate("2020/20/20"));
    }

    @Test
    public void testDateRange() {
        assertEquals(false, new Parser().isValidDate("2020-99-99"));
    }
}