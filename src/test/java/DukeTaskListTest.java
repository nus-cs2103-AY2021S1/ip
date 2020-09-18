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
        taskList.addTask(new Deadline("Essay", " 11/10/2020 1800", TaskType.DEADLINE));
        taskList.addTask(new Event("Meeting", " 20/06/2020 1400-1600", TaskType.EVENT));
        String todo = "[Todo][\u2718] Finish Drawing - Priority: UNDEFINED";
        String deadline = "[Deadline][\u2718] Essay (by: 11 Oct 2020 18:00) - Priority: UNDEFINED";
        String event = "[Event][\u2718] Meeting (at: 20 Jun 2020 14:00-16:00) - Priority: UNDEFINED";

        assertEquals(todo, taskList.getTask(0).toString());
        assertEquals(deadline, taskList.getTask(1).toString());
        assertEquals(event, taskList.getTask(2).toString());
    }

}
