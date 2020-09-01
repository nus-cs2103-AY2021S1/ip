package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventToWriteTest() {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Event("project meeting ", LocalDateTime.parse("2020-08-30 18:00", validFormat), false));
        TaskList tl = new TaskList(taskList);
        String eventToWrite = "E | " + '0' + " | project meeting | 2020-08-30 18:00";
        String actual = tl.taskList.get(0).toWrite();
        assertEquals(eventToWrite, actual);
    } 
}
