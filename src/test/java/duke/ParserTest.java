package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseString() {
        Parser parser = new Parser();
        String testString = "A B C  ";
        String[] expected = new String[]{"A","B","C"};
        String[] actual = parser.parseString(testString);

        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
        assertEquals(expected[2],actual[2]);
    }

    

}
