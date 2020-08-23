import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParse() {
        try {
            //we converted to string to compare because using String[] compares the address instead
            assertEquals(Arrays.toString(new String[]{"event", "play games ", "2020-08-09", "1700", "1800"}),
                    Arrays.toString(new Parser().parse("event play games /at 2020-08-09 1700-1800")));

            assertEquals(Arrays.toString(new String[]{"todo", "sing song"}),
                    Arrays.toString(new Parser().parse("todo sing song")));

            assertEquals(Arrays.toString(new String[]{"deadline", "study ", "2020-08-09", "1800"}),
                    Arrays.toString(new Parser().parse("deadline study /by 2020-08-09 1800")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
