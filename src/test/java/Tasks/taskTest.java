package Tasks;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


class taskTest {
    final task t1 = new Todo("Test 1","[T]");
    final task t2 = new Deadline("Test 2","[D]");
    final task t3 = new Event("Test 3","[E]");

    final task t1Done = new Todo("Test 1","[T]");
    final task t2Done = new Deadline("Test 2","[D]");
    final task t3Done = new Event("Test 3","[E]");

    @Test
    void taskName() {
        assertEquals("Test 1",t1.taskName());
        assertEquals("Test 2",t2.taskName());
        assertEquals("Test 3",t3.taskName());
    }

    @Test
    void taskCompleted() {
        t1Done.done();
        t2Done.done();
        t3Done.done();
        assertEquals(false,t1.taskCompleted());
        assertEquals(false,t2.taskCompleted());
        assertEquals(false,t3.taskCompleted());
        assertEquals(true,t1Done.taskCompleted());
        assertEquals(true,t2Done.taskCompleted());
        assertEquals(true,t3Done.taskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[T]",t1.taskType());
        assertEquals("[D]",t2.taskType());
        assertEquals("[E]",t3.taskType());
    }

    @Test
    void taskDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt1 = LocalDateTime.parse("5-12-2020 1200",format);
        LocalDateTime dt2 = LocalDateTime.parse("6-12-2020 1200",format);
        t2.setDate(dt1);
        t3.setDate(dt2);

        assertEquals(null,t1.taskDate());
        assertEquals(dt1,t2.taskDate());
        assertEquals(dt2,t3.taskDate());
    }
}