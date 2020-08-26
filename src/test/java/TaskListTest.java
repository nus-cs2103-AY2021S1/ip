import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {



    @Test
    public void listTest() throws DukeEmptyDescException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hi", TaskType.TODO, false), false);
        assertEquals(taskList.toString(), "Here are the tasks in your list:\n" +
                "1.[✘] Hi\n");
    }

    @Test
    public void listTest2() throws DukeEmptyDescException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hello", TaskType.TODO, true), false);
        assertEquals(taskList.toData(),"[✓] Hello\n");
    }

    @Test
    public void listTest3() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hi", TaskType.TODO, false), false);
        taskList.addTask(new Deadline("Deadline", " 12-12-1212 1212", false), false);
        assertEquals(taskList.toString(), "Here are the tasks in your list:\n" +
                "1.[✘] Hi\n" +
                "2.[D][✘] Deadline(by: 12-12-1212 1212)\n");
    }

}