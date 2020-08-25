import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

public class ParserTest {

    @Test
    public void testFind() {
        try {
            ArrayList<String> expectedList = new ArrayList<>();
            expectedList.add("Find");
            expectedList.add("book");
            Parser parser = new Parser();
            assertEquals(expectedList, parser.processString("find book", 10));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDate() {
        try {
            Parser parser = new Parser();
            assertEquals("Oct 15 2020 6.30pm", parser.processDate("2020-10-15 1830"));
        } catch (DateException e) {
            fail();
        }
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(InvalidTaskArgumentException.class,
                () -> new Parser().processString("event project meeting", 10));
    }
}
