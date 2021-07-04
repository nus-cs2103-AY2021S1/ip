import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {



    @Test
    public void listTest() throws DukeEmptyDescException, DukeDuplicateTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hi", TaskType.TODO, false), false);
        assertEquals(taskList.toString(), "Here are the tasks in your list:\n" +
                "1.[X] Hi\n");
    }

    @Test
    public void listTest2() throws DukeEmptyDescException, DukeDuplicateTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hello", TaskType.TODO, true), false);
        assertEquals(taskList.toData(),"[O] Hello\n");
    }

    @Test
    public void listTest3() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Hi", TaskType.TODO, false), false);
        Deadline.newDeadline("Deadline 12-12-1212 1212", taskList, false,false);
        assertEquals(taskList.toString(), "Here are the tasks in your list:\n" +
                "1.[X] Hi\n" +
                "2.[D][X] Deadline(by: 12-12-1212 1212)\n");
    }

}