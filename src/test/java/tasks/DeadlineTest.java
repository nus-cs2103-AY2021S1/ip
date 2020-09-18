package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import mattbot.tasks.Deadline;
import mattbot.tasks.Task;



class DeadlineTest {
    final Task t2 = new Deadline("Test 2", "[D]");
    final Task t2Done = new Deadline("Test 2", "[D]");

    @Test
    void taskName() {
        assertEquals("Test 2", t2.getTaskName());
    }

    @Test
    void taskCompleted() {
        t2Done.setDone();
        assertEquals(false, t2.getTaskCompleted());
        assertEquals(true, t2Done.getTaskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[D]", t2.getTaskType());
    }

    @Test
    void taskDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt1 = LocalDateTime.parse("5-12-2020 1200", format);
        t2.setDate(dt1);
        assertEquals(dt1, t2.getTaskDate());
    }
}
