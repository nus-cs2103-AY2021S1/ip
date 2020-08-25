package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testConvertDate() {
        assertEquals("Jul 15 2000", new Parser().convertDate("2000-07-15"));
        assertEquals("Dec 25 2020", new Parser().convertDate("2020-12-25"));
        assertEquals("Wednesday", new Parser().convertDate("Wednesday"));
    }
}
