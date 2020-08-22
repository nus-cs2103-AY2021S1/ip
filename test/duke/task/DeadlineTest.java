package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadline = new Deadline("Lunch with friends", "2020-02-23");
    Deadline deadline2 = new Deadline("Meeting with prof", true, "2019-05-23");

    @Test
    public void testMarkAsDone() {
        deadline.markAsDone();
        assertEquals("[D][✓] Lunch with friends (by: Feb 23 2020)", deadline.toString());

        deadline2.markAsDone();
        assertEquals("[D][✓] Meeting with prof (by: May 23 2019)", deadline2.toString());
    }

    @Test
    public void testToString() {
        assertEquals("[D][✘] Lunch with friends (by: Feb 23 2020)", deadline.toString());
        assertEquals("[D][✓] Meeting with prof (by: May 23 2019)", deadline2.toString());
    }

    @Test
    public void toCustomString() {
        assertEquals("D | 0 | Lunch with friends | 2020-02-23", deadline.toCustomString());
        assertEquals("D | 1 | Meeting with prof | 2019-05-23", deadline2.toCustomString());
    }

}