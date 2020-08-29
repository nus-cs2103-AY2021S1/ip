package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static Parser testParser = new Parser();
    private static Task testToDo = new ToDo("call mom");
    private static Task testDeadline = new Deadline("homework", LocalDate.parse("2020-08-29"));
    private static Task testEvent = new Event("seminar", LocalDate.parse("2020-10-10"));
    private static ArrayList<Task> tasks = new ArrayList<>();


    static {
        testToDo.markAsDone();
        testDeadline.markAsDone();
        tasks.add(testToDo);
        tasks.add(testDeadline);
        tasks.add(testEvent);
    }

    private static TaskList testTaskList = new TaskList(tasks);

    @Test
    public void getTasks_fromTestTaskList_eachTaskToStringIsCorrect() {
        List<Task> tasks = testTaskList.getTasks();
        assertEquals("[T][✓] call mom", tasks.get(0).toString());
        assertEquals("[D][✓] homework (by: Aug 29 2020)", tasks.get(1).toString());
        assertEquals("[E][✘] seminar (at: Oct 10 2020)", tasks.get(2).toString());
    }

    @Test
    public void isWithinValidRange_variousIndex_onlyValidIndexReturnsTrue() {
        assertTrue(testTaskList.isWithinValidRange(0));
        assertTrue(testTaskList.isWithinValidRange(1));
        assertTrue(testTaskList.isWithinValidRange(2));
        assertFalse(testTaskList.isWithinValidRange(3));
        assertFalse(testTaskList.isWithinValidRange(4));
    }

    @Test
    public void incrementPendingTasks_fromTestTaskList_turnsOneToTwo() throws CloneNotSupportedException {
        TaskList taskList = (TaskList) testTaskList.clone();
        taskList.incrementPendingTasks();
        assertEquals(2, taskList.getNumOfPendingTasks());
    }

    @Test
    public void decrementPendingTasks_fromTestTaskList_turnsOneToZero() throws CloneNotSupportedException {
        TaskList taskList = (TaskList) testTaskList.clone();
        taskList.decrementPendingTasks();
        assertEquals(0, taskList.getNumOfPendingTasks());
    }

    @Test
    public void getTaskID_variousParserVariousIndex_oneIndexBelowReturned() throws Exception {
        TaskList taskList = (TaskList) testTaskList.clone();
        testParser.setCommandLine("done 1");
        assertEquals(0, taskList.getTaskID(testParser));
        testParser.setCommandLine("delete 2");
        assertEquals(1, taskList.getTaskID(testParser));
    }

    @Test
    public void done_indexThree_eventTaskDone() throws Exception {
        TaskList taskList = (TaskList) testTaskList.clone();
        testParser.setCommandLine("done 3");
        taskList.done(testParser);
        assertTrue(taskList.getTasks().get(2).isDone);
    }

    @Test
    public void done_indexOne_toDoTaskAlreadyDone() throws Exception {
        TaskList taskList = (TaskList) testTaskList.clone();
        testParser.setCommandLine("done 1");
        taskList.done(testParser);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void delete_indexThree_pendingCountBecomesZero() throws Exception {
        TaskList taskList = (TaskList) testTaskList.clone();
        testParser.setCommandLine("delete 3");
        taskList.delete(testParser);
        assertEquals(0, taskList.getNumOfPendingTasks());
    }

    @Test
    public void add_toDoItem_pendingCountBecomesTwo() throws Exception {
        TaskList taskList = (TaskList) testTaskList.clone();
        testParser.setCommandLine("todo call dad");
        taskList.add(testParser);
        assertEquals(2, taskList.getNumOfPendingTasks());
    }

}
