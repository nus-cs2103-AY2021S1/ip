package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    final task t2 = new Deadline("Test 2","[D]");
    final task t2Done = new Deadline("Test 2","[D]");

    @Test
    void taskName() {
        assertEquals("Test 2",t2.taskName());
    }

    @Test
    void taskCompleted() {
        t2Done.done();
        assertEquals(false,t2.taskCompleted());
        assertEquals(true,t2Done.taskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[D]",t2.taskType());
    }

    @Test
    void taskDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt1 = LocalDateTime.parse("5-12-2020 1200",format);
        t2.setDate(dt1);
        assertEquals(dt1,t2.taskDate());
    }
}