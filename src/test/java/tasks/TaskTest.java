package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import mattbot.tasks.Deadline;
import mattbot.tasks.Event;
import mattbot.tasks.Task;
import mattbot.tasks.Todo;





class TaskTest {
    final Task t1 = new Todo("Test 1", "[T]");
    final Task t2 = new Deadline("Test 2", "[D]");
    final Task t3 = new Event("Test 3", "[E]");

    final Task t1Done = new Todo("Test 1", "[T]");
    final Task t2Done = new Deadline("Test 2", "[D]");
    final Task t3Done = new Event("Test 3", "[E]");

    @Test
    void taskName() {
        assertEquals("Test 1", t1.getTaskName());
        assertEquals("Test 2", t2.getTaskName());
        assertEquals("Test 3", t3.getTaskName());
    }

    @Test
    void taskCompleted() {
        t1Done.setDone();
        t2Done.setDone();
        t3Done.setDone();
        assertEquals(false, t1.getTaskCompleted());
        assertEquals(false, t2.getTaskCompleted());
        assertEquals(false, t3.getTaskCompleted());
        assertEquals(true, t1Done.getTaskCompleted());
        assertEquals(true, t2Done.getTaskCompleted());
        assertEquals(true, t3Done.getTaskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[T]", t1.getTaskType());
        assertEquals("[D]", t2.getTaskType());
        assertEquals("[E]", t3.getTaskType());
    }

    @Test
    void taskDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt1 = LocalDateTime.parse("5-12-2020 1200", format);
        LocalDateTime dt2 = LocalDateTime.parse("6-12-2020 1200", format);
        t2.setDate(dt1);
        t3.setDate(dt2);

        assertEquals(null, t1.getTaskDate());
        assertEquals(dt1, t2.getTaskDate());
        assertEquals(dt2, t3.getTaskDate());
    }
}
