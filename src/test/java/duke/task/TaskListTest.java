package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    ArrayList<Task> actualTasks = new ArrayList<>();
    StorageStub storageStub = new StorageStub();
    TaskList taskList;
    String dummyTask = "deadline week 3 ip and tp /by 26-8-2020 wednesday 2359";
    
    @Test
    public void addTaskTest() {
        taskList.addTask(TaskList.Type.DEADLINE, dummyTask.split(" "));
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("week 3 ip and tp", "26-8-2020 wednesday 2359"));
        assertEquals(actualTasks, expected);
    }
    
    @Test
    public void modifyTaskTest() {
        taskList.addTask(TaskList.Type.DEADLINE, dummyTask.split(" "));
        taskList.modifyTask(TaskList.Action.DONE, "0");
        ArrayList<Task> expected = new ArrayList<>();
        Deadline deadline = new Deadline("week 3 ip and tp", "26-8-2020 wednesday 2359");
        deadline.markDone();
        expected.add(deadline);
        assertEquals(actualTasks, expected);
    }
    
    @BeforeEach
    public void init() {
        taskList = new TaskList(actualTasks, storageStub);
    }
    
    @AfterEach
    public void cleanUp() {
        actualTasks = new ArrayList<>();
    }
    
}
