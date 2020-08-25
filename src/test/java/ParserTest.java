import duke.Parser;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseInt() {
        assertEquals(Parser.parseInt("123"), 123);
    }

    @Test
    public void parseDate() {
        try {
            Calendar calendar = Calendar.getInstance();
            assertEquals(
                    Parser.parseDate("12-04-1998").getTime(),
                    883238400000L
                    );
        } catch (Exception e) {
            fail();
        }
    }
}
