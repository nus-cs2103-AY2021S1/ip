import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;

public class TaskTest {

    @Test
    public void todoTaskTest() {
        String description = "Task Example";
        TodoTask todoTask = new TodoTask(description);

        assertEquals(description, todoTask.getDescription());

        assertEquals("[T][X] Task Example", todoTask.toString());

        todoTask.setDone(true);
        assertEquals("[T][O] Task Example", todoTask.toString());
    }

    @Test
    public void deadlineTaskTest() {
        String description = "Task Example";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("2020-03-04 18:00", formatter);
        DeadlineTask deadlineTask = new DeadlineTask(description, localDateTime);

        assertEquals(description, deadlineTask.getDescription());
        assertEquals(localDateTime, deadlineTask.getDateTime());
        assertEquals("[D][X] Task Example (by: Mar 4 2020, 6 PM)", deadlineTask.toString());

        deadlineTask.setDone(true);
        assertEquals(true, deadlineTask.isDone());
        assertEquals("[D][O] Task Example (by: Mar 4 2020, 6 PM)", deadlineTask.toString());
    }

    @Test
    public void eventTaskTest() {
        String description = "Task Example";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("2020-03-04 18:00", formatter);
        EventTask eventTask = new EventTask(description, localDateTime);

        assertEquals(description, eventTask.getDescription());
        assertEquals(localDateTime, eventTask.getDateTime());
        assertEquals("[E][X] Task Example (by: Mar 4 2020, 6 PM)", eventTask.toString());

        eventTask.setDone(true);
        assertEquals(true, eventTask.isDone());
        assertEquals("[E][O] Task Example (by: Mar 4 2020, 6 PM)", eventTask.toString());
    }
}
