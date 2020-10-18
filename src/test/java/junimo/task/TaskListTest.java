package junimo.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    public enum SampleTask {
        DEADLINE(new Deadline("Math Test", "2020-08-31", true)),
        EVENT(new Event("Pool Party", "2020-12-12 12:00", "2020-12-12 18:00", false)),
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
    public void constructor() {
        TaskList taskList = new TaskList();
        assertEquals(new ArrayList<Task>(), taskList.getTaskList());
    }

    @Test
    public void addTask_validArguments() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task);
        }
        assertEquals(getCorrectFormatExpectedTaskList(), taskList.getTaskList());
    }

    @Test
    public void markTaskAsDone() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task);
        }
        taskList.markTaskAsDone("1");
        assertEquals("[\u2713]", taskList.getTaskList().get(0).getCheckBox());
    }

    @Test
    public void deleteTask() {
        TaskList taskList = new TaskList();
        for (SampleTask sampleTask : SampleTask.values()) {
            taskList.addTask(sampleTask.task);
        }
        taskList.deleteTask("2");
        List<Task> expectedTaskList = getCorrectFormatExpectedTaskList();
        expectedTaskList.remove(1);
        assertEquals(expectedTaskList, taskList.getTaskList());
    }
}
