package duke.tasklist;

import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void setDoneTest() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("todo1"));
        taskList.add(new ToDo("todo2"));
        taskList.add(new ToDo("todo3"));
        assertEquals("[T][\u2713] todo3", taskList.setDone(3).toString());
    }
}
