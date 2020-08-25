package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    public static DateTimeFormatter VALID_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringMessageTest1() {
        assertEquals(new Deadline("assignment 1", LocalDateTime.parse("2020-08-26 23:59", VALID_FORMAT), false).toString()
        , "[D][✘] assignment 1 (by: Aug 26 2020 23:59)");
    }

    @Test
    public void toStringMessageTest2() {
        assertEquals(new Event("party", LocalDateTime.parse("2020-10-10 18:59", VALID_FORMAT), false).toString()
                , "[E][✘] party (at: Oct 10 2020 18:59)");
    }

}
