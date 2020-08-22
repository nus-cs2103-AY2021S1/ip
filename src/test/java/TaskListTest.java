import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Deadline dummyDeadline = new Deadline("get drunk", LocalDate.parse("2020-12-25"));
        tasks.addTask(dummyDeadline);
        assertEquals(dummyDeadline, tasks.getTaskList().get(0));
    }
}
