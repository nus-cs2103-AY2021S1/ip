package duke;

import main.java.duke.Deadline;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest1() {
        Deadline deadline = new Deadline("Complete assignment /by 2019-10-15 1233");
        assertEquals("[D][✘] Complete assignment (by: OCTOBER 15 2019, 12:33 PM)", deadline.toString());
    }

    @Test
    public void toStringTest2() {
        Deadline deadline = new Deadline("Have breakfast /by 2019-10-15 0800");
        assertEquals("[D][✘] Have breakfast (by: OCTOBER 15 2019, 08:00 AM)", deadline.toString());
    }
}
