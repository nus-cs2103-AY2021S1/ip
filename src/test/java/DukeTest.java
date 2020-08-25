import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void test1() {
        Event e = new Event("dance", "2020-08-09");
        assertEquals(e.inputStyle(), "event false dance /at 2020-08-09");
    }

    @Test
    public void test2() {
        Task e = new Deadline("finish ip ", "2020-08-25");
        assertEquals(e.inputStyle(), "deadline false finish ip /by 2020-08-25");
    }
}
