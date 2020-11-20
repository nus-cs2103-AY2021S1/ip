import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Task;
import duke.TaskList;

public class TaskListTest {

    @Test
    public void taskListTest() {
        TaskList taskList = TaskList.startList();
        assertEquals("Got it. I've added this task:\n"
                        + "\t[X] eat food\n"
                        + "\tNow you have 1 tasks in the list.",
                taskList.addToList(new Task("eat food")));
        try {
            assertEquals("Noted. I've removed this task:\n"
                            + "\t[X] eat food\n"
                            + "\tNow you have 0 tasks in the list.",
                    taskList.remove(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
