package Duke.Tool;

import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void deleteTest() throws DukeException {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Event("project meeting ", LocalDateTime.parse("2020-08-30 18:00",  validFormat), false));
        TaskList tl = new TaskList(taskList);
        new Command().delete(1, tl);
        assertEquals(0, tl.getSize());
    }
}
