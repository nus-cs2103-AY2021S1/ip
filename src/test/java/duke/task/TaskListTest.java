package duke.task;

import duke.command.Command;
import duke.io.Layout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    ArrayList<Task> actualTasks = new ArrayList<>();
    StorageStub storageStub = new StorageStub();
    Layout layout = new Layout();
    TaskList taskList;
    String dummyAddTask = "deadline week 3 ip and tp /by 26-8-2020 wednesday 2359";
    String dummyMarkDoneTask = "done 1";
    
    @Test
    public void readCommandAddTaskTest() {
        Command command;
        try {
            command = taskList.getCommands(dummyAddTask.split(" "));
            command.execute(actualTasks, layout);
        } catch (DukeException e) {
            //not supposed to enter here
            System.out.println("Not supposed to enter");
        }
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("week 3 ip and tp", "26-8-2020 wednesday 2359"));
        assertEquals(actualTasks, expected);
    }
    
    @Test
    public void readCommandManageTaskTest() {
        Command addCommand;
        Command markDoneCommand;
        try {
            addCommand = taskList.getCommands(dummyAddTask.split(" "));
            addCommand.execute(actualTasks, layout);
            markDoneCommand = taskList.getCommands(dummyMarkDoneTask.split(" "));
            markDoneCommand.execute(actualTasks, layout);
        } catch (DukeException e) {
            //not supposed to enter here
            System.out.println("Not supposed to enter");
        }
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
