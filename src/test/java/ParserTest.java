import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import duke.main.Parser;

public class ParserTest {
    @Test
    public void hasTime_reallyHasTime_returnsTrue() {
        assertTrue(Parser.hasTime("2020-10-08 12:00"));
    }

    @Test
    public void hasTime_doesNotHaveTime_returnsFalse() {
        assertFalse(Parser.hasTime("2020-10-08"));
    }
}
