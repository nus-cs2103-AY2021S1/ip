import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import util.DukeException;
import util.TaskList;
import util.task.Task;
import util.task.Todo;

public class TaskListTest {
    @Test
    public void markTaskDone_invalidIndex_exceptionThrown() {
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new Todo("return book"));
        TaskList taskTester = new TaskList(testTasks);
        String actual = "";
        try {
            taskTester.markTaskDone("done 0");
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "Sorry, I don't think that's a valid index...";
        assertEquals(expected, actual);
    }
}
