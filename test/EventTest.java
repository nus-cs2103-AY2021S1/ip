import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class EventTest {
    @Test
    void testToString() {
        Event newEvent = new Event("Read Douluo Dalu ", LocalDate.parse("2019-10-25"));
        assertEquals("[E][✘] Read Douluo Dalu (at: Oct 25 2019)", newEvent.toString());
    }

    @Test
    void testFileTask() {
        Event newEvent = new Event("Read Doupo Cangqiong ", "Dec 15 2018", true);
        assertEquals("[E][✓] Read Doupo Cangqiong (at: Dec 15 2018)", newEvent.toString());
    }

    @Test
    void testAddTask() {
        Event newEvent = new Event("Read Wudong Qiankun ", LocalDate.parse("2019-10-25"));
        assertEquals("[E][✘] Read Wudong Qiankun (at: Oct 25 2019)", newEvent.toString());
    }
}