import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParsing() {
        try {
            assertTrue(Parser.parse("event kiwis /at 2020-08-30 18:00") instanceof Command);
        } catch (InvalidInputException e) {
            fail();
        }
    }

    @Test
    public void testError() {
        try {
            Parser.parse("kiwis /at 2020-08-40 18:00");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",e.getMessage());
        }
    }
}
