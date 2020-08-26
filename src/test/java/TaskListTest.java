import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {



    @Test
    public void descTestOne() throws DukeEmptyDescException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hi", TaskType.TODO, false), false);
        assertEquals(taskList.toString(), "Here are the tasks in your list:\n" +
                "1.[✘] Hi\n");
    }

    @Test
    public void descTestTwo() throws DukeEmptyDescException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hello", TaskType.TODO, true), false);
        assertEquals(taskList.toData(),"[✓] Hello\n");
    }

}