package duke;

import main.java.duke.Deadline;
import main.java.duke.TaskList;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void addTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(true, taskList.addTask(new Deadline("Complete assignment /by 2019-10-15 1233")));
    }
}
