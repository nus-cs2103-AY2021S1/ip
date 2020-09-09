import duke.tool.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {

    @Test
    public void testParse() {
        Parser p = new Parser();
        String[] arr = new String[]{"todo", "return book"};
        assertArrayEquals(arr, p.parse("todo return book"));
    }
}
