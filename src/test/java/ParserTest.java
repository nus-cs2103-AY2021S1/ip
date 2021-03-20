import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testFind() {
        try {
            ArrayList<String> expectedList = new ArrayList<>();
            expectedList.add("Find");
            expectedList.add("book");
            Parser parser = new Parser();
            assertEquals(expectedList, parser.parseUserCommand("find book", 10));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDate() {
        try {
            Parser parser = new Parser();
            assertEquals("Oct 15 2020 06:30 PM", parser.parseDateTime("2020-10-15 1830"));
        } catch (DateException e) {
            fail();
        }
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(InvalidTaskArgumentException.class, () -> new Parser()
                .parseUserCommand("event project meeting", 10));
    }
}
