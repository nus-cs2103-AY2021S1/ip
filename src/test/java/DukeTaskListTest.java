import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.Todo;

public class DukeTaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    public void generateEmptyTaskListTest() {
        assertEquals(true, taskList.isEmpty());
    }

    @Test
    public void addTasksTest() {
        taskList.addTask(new Todo("Finish Drawing", TaskType.TODO));
        String todo = "[Todo][\u2718] Finish Drawing - Priority: UNDEFINED";

        assertEquals(todo, taskList.getTask(0).toString());
    }

}
