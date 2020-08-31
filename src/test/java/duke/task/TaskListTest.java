package duke.task;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    
    public enum SampleTask {
        EVENT("event Pool Party /at Matt's House", false, 
                new Event("Pool Party", "Matt's House", false)),
        DEADLINE("deadline Math Test /by 2020-08-31", true, 
                new Deadline("Math Test", "2020-08-31", true)),
        TODO("todo Write English Essay", false, 
                new Todo("Write English Essay", false));
        
        final String command;
        boolean isDone;
        final Task task;
        
        SampleTask(String command, boolean isDone, Task task) {
            this.command = command;
            this.isDone = isDone;
            this.task = task;
        }
        
        String getStoredString() {
            return this.command + System.lineSeparator() + this.isDone + System.lineSeparator();
        }
    }

    public static String getCorrectFormatSampleData() {
        StringBuilder data = new StringBuilder();
        for (SampleTask sample : SampleTask.values()) {
            data.append(sample.getStoredString());
        }
        return data.toString();
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
            taskList.add(sampleTask.command, sampleTask.isDone, false);
        }
        assertEquals(getCorrectFormatExpectedTaskList(), taskList.getTaskList());
    }
    
    @Test
    public void correctFormatBufferedReaderConstuctorTest() {
        BufferedReader br = new BufferedReader(new StringReader(getCorrectFormatSampleData()));
        TaskList taskList = new TaskList(br);
        assertEquals(getCorrectFormatExpectedTaskList(), taskList.getTaskList());
    }
    
    @Test
    public void markTaskAsDoneTest() {
        BufferedReader br = new BufferedReader(new StringReader(getCorrectFormatSampleData()));
        TaskList taskList = new TaskList(br);
        taskList.markTaskAsDone("1");
        assertEquals("[\u2713]", taskList.getTaskList().get(0).getCheckBox());
    }
    
    @Test
    public void deleteTaskTest() {
        BufferedReader br = new BufferedReader(new StringReader(getCorrectFormatSampleData()));
        TaskList taskList = new TaskList(br);
        taskList.deleteTask("2");
        List<Task> expectedTaskList = getCorrectFormatExpectedTaskList();
        expectedTaskList.remove(1);
        assertEquals(expectedTaskList, taskList.getTaskList());
    }
    
}
