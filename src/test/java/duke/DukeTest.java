package duke;

import java.time.format.FormatStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void dateTimeConverter_processTime_success() {
        assertEquals(
                new DateTimeConverter(FormatStyle.MEDIUM,
                        FormatStyle.SHORT).processTime("todo", "10/12/2020 1300"),
                "Dec 10, 2020, 1:00 PM");
    }

    @Test
    public void parser_processTime_success() {
        String[] orig = new Parser().commandParser("deadline finish assignments /by 2020-12-10 1500");
        String[] actual = new String[]{"deadline", "finish assignments", "Dec 10, 2020, 3:00 PM"};
        assertEquals(orig[0], actual[0]);
        assertEquals(orig[1], actual[1]);
        assertEquals(orig[2], actual[2]);
    }

    @Test
    public void deadline_getInfo_success() {
        String[] orig = new Deadline("read books", "Dec 10, 2020, 3:00 PM").getInfo();
        String[] actual = new String[]{"D", "0", "read books", "Dec 10, 2020, 3:00 PM"};
        assertEquals(orig[0], actual[0]);
        assertEquals(orig[1], actual[1]);
        assertEquals(orig[2], actual[2]);
        assertEquals(orig[3], actual[3]);
    }
}
