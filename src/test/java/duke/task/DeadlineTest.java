package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void initialiseNewDeadline_notDone_pass() {
        Deadline testDeadline = new Deadline("0", "read book",
                "2020-08-31 6pm");
        String expectedOutput = "[D][✗] read book (by: Aug 31 2020 6pm)";
        assertEquals(expectedOutput, testDeadline.toString());
    }

    @Test
    public void initialiseNewDeadline_done_pass() {
        Deadline testDeadline = new Deadline("1", "read book",
                "2020-08-31 6pm");
        String expectedOutput = "[D][✓] read book (by: Aug 31 2020 6pm)";
        assertEquals(expectedOutput, testDeadline.toString());
    }
}