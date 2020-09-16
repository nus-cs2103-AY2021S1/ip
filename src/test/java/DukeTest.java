import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Event;
import duke.Task;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void test1() {
        Event e = new Event("dance", "2020-08-09");
        assertEquals(e.inputStyle(), "event false dance /at 2020-08-09 #DEFAULT");
    }

    @Test
    public void test2() {
        Task e = new Deadline("finish ip ", "2020-08-25");
        assertEquals(e.inputStyle(), "deadline false finish ip /by 2020-08-25 #DEFAULT");
    }
}
