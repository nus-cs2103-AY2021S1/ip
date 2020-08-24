package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
    
    @Test
    public void testIsDate() {
        assertTrue(new Parser().isDate("2020-08-24"));
    }
    
    @Test
    public void testConvertDate() {
        assertEquals("Aug 24 2020", new Parser().convertDate("2020-08-24"));
        assertEquals("Dec 25 0001", new Parser().convertDate("0001-12-25"));
    }
}
