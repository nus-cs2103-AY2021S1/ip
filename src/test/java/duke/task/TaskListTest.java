package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void size() {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("sleep", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        assertEquals(1, taskList.size());
    }

    @Test
    void get() {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("sleep", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        assertEquals("[T][✗] sleep", taskList.get(0).toString());
    }

    @Test
    void remove() {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("sleep", TaskType.TODO));
        taskArrayList.add(new Task("study", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        assertEquals("[T][✗] study", taskList.remove(1).toString());
    }

    @Test
    void add() {
        List<Task> taskArrayList = new ArrayList<>();
        Task sleep = new Task("sleep", TaskType.TODO);
        taskArrayList.add(new Task("study", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        taskList.add(1, sleep);
        assertEquals("[T][✗] sleep", taskList.remove(1).toString());
    }

    @Test
    void addTask() {
        List<Task> taskArrayList = new ArrayList<>();
        Task sleep = new Task("sleep", TaskType.TODO);
        taskArrayList.add(new Task("study", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(sleep);
        assertEquals(2, taskList.size());
    }

    @Test
    void deleteTask() {
        List<Task> taskArrayList = new ArrayList<>();
        Task sleep = new Task("sleep", TaskType.TODO);
        taskArrayList.add(new Task("study", TaskType.TODO));
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(sleep);
        taskList.deleteTask("delete 1");
        assertEquals(1, taskList.size());
    }
}