package duke.task;

import main.java.duke.task.Deadline;
import main.java.duke.task.TaskList;
import main.java.duke.task.Todo;
import main.java.duke.task.Event;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    void getSize_normalInput_success() throws Exception {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getSize());
        tasks.addTask(new Todo("todo1"));
        tasks.addTask(new Deadline("deadline1", LocalDate.parse("2020-09-23")));
        assertEquals(2, tasks.getSize());
        tasks.addTask(new Event("event1", LocalDate.parse("2020-10-22")));
        assertEquals(3, tasks.getSize());
    }

    @Test
    void getTask_normalInput_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("todo1"));
        Deadline deadline = new Deadline("deadline1", LocalDate.parse("2020-09-23"));
        tasks.addTask(deadline);
        tasks.addTask(new Event("event1", LocalDate.parse("2020-10-22")));
        assertEquals(deadline, tasks.getTask(1));
    }
}
