package duke.test;


import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    private static final char DONE = '\u2713';
    private static final char NOT_DONE = '\u2717';

    // @Test
    // public void dummyTest() {
    //     assertEquals(2, 2);
    // }


    @Test
    public void Deadline_NotDone_toString_printedCorrectly() {
        Deadline testDeadline = new Deadline("stuff /by 2020-01-01");
        String expected = String.format("[D][%c] %s (by: %s)", NOT_DONE, "stuff", "01 Jan 2020");
        assertEquals(expected, testDeadline.toString());
    }


    @Test
    public void Deadline_Done_toString_printedCorrectly() {
        Deadline testDeadline = new Deadline("stuff /by 2020-01-01", true);
        String expected = String.format("[D][%c] %s (by: %s)", DONE, "stuff", "01 Jan 2020");
        assertEquals(expected, testDeadline.toString());
    }

}
