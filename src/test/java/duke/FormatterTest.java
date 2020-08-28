package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void formatResponseTest() {
        String response = "hi";
        String actual = Formatter.format_response(response);
        String expected =
                "____________________________________________________________\n"+
                response +
                "____________________________________________________________\n";
        assertEquals(actual.compareTo(expected), 0);
    }
}
