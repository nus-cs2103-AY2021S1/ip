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
    String dummyAddTask = "deadline week 3 ip and tp /by 26-8-2020 wednesday 2359";
    String dummyMarkDoneTask = "done 1";
    
    @Test
    public void readCommandAddTaskTest() {
        taskList.readCommands(null, dummyAddTask.split(" "));
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("week 3 ip and tp", "26-8-2020 wednesday 2359"));
        assertEquals(actualTasks, expected);
    }
    
    @Test
    public void readCommandManageTaskTest() {
        taskList.readCommands(null, dummyAddTask.split(" "));
        taskList.readCommands(null, dummyMarkDoneTask.split(" "));
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
