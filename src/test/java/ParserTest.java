import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;

public class ParserTest {
    @Test
    public void parseInt() {
        assertEquals(Parser.parseInt("123"), 123);
    }

    @Test
    public void parseDate() {
        try {
            long actual = Parser
                    .parseDate("12-04-1998")
                    .getTime();
            long expected = 883238400000L;
            assertEquals(actual, expected);
        } catch (Exception e) {
            fail();
        }
    }
}
