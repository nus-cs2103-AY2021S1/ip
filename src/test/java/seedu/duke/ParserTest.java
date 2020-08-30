package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseTest_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("delete"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("ERROR!! Huh?? What are you trying to do??".replaceAll("(?m)^", "\t"), e.getMessage());
        }
    }
}
