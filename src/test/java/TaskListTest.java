import duke.exception.MissingDeadlineException;
import duke.exception.MissingTaskException;
import duke.task.Task;
import duke.tool.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_presentDeadline_success() throws MissingDeadlineException {
        List<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        Task task = tasks.addTask("event", "project meeting /at 2020-08-27");
        assertEquals("[E][✗] project meeting (at: Aug 27 2020)", task.toString());
    }

    @Test
    public void completeTask_negativeIndex_exceptionThrown() {
        List<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        try {
            assertEquals(0, tasks.completeTask(-1));
        } catch (MissingTaskException e) {
            String message = "Something went wrong... \n " + "There's no task with the tag -1 (・・;)ゞ";
            assertEquals(message, e.toString());
        }
    }
}
