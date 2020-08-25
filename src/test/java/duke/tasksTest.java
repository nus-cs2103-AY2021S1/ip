package duke;
import duke.tasks.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class tasksTest {



    @Test
    public void add_addTodoTask_todoTaskAdded(){
        TaskList testTaskList =  new TaskList();
        Todo testTask = new Todo("borrow lamp");
        testTaskList.add(testTask);
        assertEquals("borrow lamp", testTaskList.getTaskLs().get(0).getDescription());
    }

    @Test
    public void event_checkStatusOfTask_eventTaskDone() {
        TaskList testTaskList =  new TaskList();
        Event testTask = new Event("borrow books", "2018-08-08");
        testTaskList.add(testTask);
        testTaskList.getTaskLs().get(0).markAsDone();
        assertEquals(true, testTaskList.getTaskLs().get(0).isDone());
    }

    @Test
    public void doneCommand_checkIfExitDuke_falseExit() {
        DoneCommand testDoneCommand = new DoneCommand("testing");
        assertEquals(false, testDoneCommand.isExit());
    }

}