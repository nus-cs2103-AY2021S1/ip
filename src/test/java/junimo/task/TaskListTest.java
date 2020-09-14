package junimo.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    public enum SampleTask {
        DEADLINE(new Deadline("Math Test", "2020-08-31", true)),
        EVENT(new Event("Pool Party", "Matt's House", false)),
        TODO(new Todo("Write English Essay", false));

        final Task task;

        SampleTask(Task task) {
            this.task = task;
        }
    }

    public static List<Task> getCorrectFormatExpectedTaskList() {
        List<Task> taskList = new ArrayList<>();
        for (SampleTask sample : SampleTask.values()) {
            taskList.add(sample.task);
        }
        return taskList;
    }

    @Test
    public void emptyConstructorTest() {
        TaskList taskList = new TaskList();
        assertEquals(new ArrayList<Task>(), taskList.getTaskList());
    }

    @Test
    public void addCorrectFormatTest() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task, false);
        }
        assertEquals(getCorrectFormatExpectedTaskList(), taskList.getTaskList());
    }

    @Test
    public void markTaskAsDoneTest() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task, false);
        }
        taskList.markTaskAsDone("1");
        assertEquals("[\u2713]", taskList.getTaskList().get(0).getCheckBox());
    }

    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task, false);
        }
        taskList.deleteTask("2");
        List<Task> expectedTaskList = getCorrectFormatExpectedTaskList();
        expectedTaskList.remove(1);
        assertEquals(expectedTaskList, taskList.getTaskList());
    }
}
