package godfather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import godfather.exception.VitoException;



public class TaskListTest {
    private static TaskList testTaskList = new TaskList();
    public static void initTest() throws VitoException {
        String[] parsedInput1 = {"T", "kill me now"};
        String[] parsedInput2 = {"T", "i want ice cream"};
        String commandTag = "T";
        testTaskList.addEntry(parsedInput1, commandTag);
        testTaskList.addEntry(parsedInput2, commandTag);
    }
    @Test
    public void modifyTaskList_addEntryTodo_increaseTaskCount() throws VitoException {
        TaskListTest.initTest();
        String[] parsedInput1 = {"T", "happy thoughts"};
        assertEquals(testTaskList.addEntry(parsedInput1, "T"),
                     "[T][\u2718] happy thoughts");
    }
    @Test
    public void queryTaskList_getCurrentStatus_returnsString() throws VitoException {
        TaskListTest.initTest();
        String expected = "Now you have 2 undone tasks in the list.";
        assertEquals(testTaskList.getCurrentStatus(), expected);
    }
    @Test
    public void modifyTaskList_completeTask_returnsString() throws VitoException {
        TaskListTest.initTest();
        String expected = "[T][\u2713] i want ice cream";
        assertEquals(testTaskList.completeTask(2), expected);
    }
}
