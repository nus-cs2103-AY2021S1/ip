package duke;

import main.java.duke.DateTimeConverter;
import main.java.duke.Parser;
import main.java.duke.Deadline;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void TestDTC(){
        assertEquals(
                new DateTimeConverter(FormatStyle.MEDIUM, FormatStyle.SHORT).processTime("10/12/2020 1300"),
                "Dec 10, 2020, 1:00 PM");
    }

    @Test
    public void TestParser(){
        String[] orig = new Parser().commandParser("deadline finish assignments /by 2020-12-10 1500");
        String[] actual = new String[]{"deadline", "finish assignments", "Dec 10, 2020, 3:00 PM"};
        assertEquals(orig[0], actual[0]);
        assertEquals(orig[1], actual[1]);
        assertEquals(orig[2], actual[2]);
    }

    @Test
    public void TestStorage(){
        String[] orig = new Deadline("read books", "Dec 10, 2020, 3:00 PM").getInfo();
        String[] actual = new String[]{"D", "0", "read books", "Dec 10, 2020, 3:00 PM"};
        assertEquals(orig[0], actual[0]);
        assertEquals(orig[1], actual[1]);
        assertEquals(orig[2], actual[2]);
        assertEquals(orig[3], actual[3]);
    }
}
