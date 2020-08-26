package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    final task t3 = new Event("Test 3","[E]");
    final task t3Done = new Event("Test 3","[E]");

    @Test
    void taskName() {
        assertEquals("Test 3",t3.taskName());
    }

    @Test
    void taskCompleted() {
        t3Done.done();
        assertEquals(false,t3.taskCompleted());
        assertEquals(true,t3Done.taskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[E]",t3.taskType());
    }

    @Test
    void taskDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt2 = LocalDateTime.parse("6-12-2020 1200",format);
        t3.setDate(dt2);
        assertEquals(dt2,t3.taskDate());
    }
}