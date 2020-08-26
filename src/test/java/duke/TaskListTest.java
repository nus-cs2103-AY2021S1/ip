package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    
    
    public static TaskList testTaskList = new TaskList();
    
    private static void initTest() throws DukeException {
        String[] parsedInput1 = {"T", "kill me now"};
        String[] parsedInput2 = {"T", "i want ice cream"};
        String commandTag = "T";
        testTaskList.addEntry(parsedInput1, commandTag);
        testTaskList.addEntry(parsedInput2, commandTag);
    }
    
    @Test
    public void modifyTaskList_addEntryTodo_increaseTaskCount() throws DukeException {
        TaskListTest.initTest();
        String[] parsedInput1 = {"T", "happy thoughts"};
        assertEquals(testTaskList.addEntry(parsedInput1, "T"),
                     "[T][✘] happy thoughts");
    }
    
    @Test
    public void queryTaskList_getCurrentStatus_returnsString() throws DukeException {
        TaskListTest.initTest();
        String expected = "Now you have 2 undone tasks in the list.";
        assertEquals(testTaskList.getCurrentStatus(), expected);
    }
    
    @Test
    public void modifyTaskList_completeTask_returnsString() throws DukeException {
        TaskListTest.initTest();
        String expected = "[T][✓] i want ice cream";
        assertEquals(testTaskList.completeTask(2), expected);
    }
}
