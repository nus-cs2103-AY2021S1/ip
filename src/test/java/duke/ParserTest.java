package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    /**
     * Tests convertDate() method in Parser class
     */
    @Test
    public void testConvertDate() {
        assertEquals("Jul 15 2000", new Parser().convertDate("2000-07-15"));
        assertEquals("Dec 25 2020", new Parser().convertDate("2020-12-25"));
        assertEquals("Wednesday", new Parser().convertDate("Wednesday"));
    }
}
