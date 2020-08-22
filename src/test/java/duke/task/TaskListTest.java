package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_taskListWithSomeItems_taskAddedAsLastItem() {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Todo("todo desc2"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        Task task = new Todo("todo desc3");
        tasks.addTask(task);

        assertEquals(task, tasks.getTask(tasks.size()));
    }

    @Test
    public void deleteTask_taskListWithSomeItems_success() {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        Task taskToDelete = new Todo("todo desc3");
        testInputTasks.add(taskToDelete);
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        int numItemsInitial = tasks.size();
        tasks.deleteTask(2);
        int numItemsAfter = tasks.size();

        assertEquals(numItemsInitial - 1, numItemsAfter );
    }

    @Test
    public void size_emptyTaskList_zero() {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        assertEquals(3, tasks.size());
    }

    @Test
    public void size_nonEmptyTaskList_nonZero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void toString_emptyTaskList_emptyString() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }

    @Test
    public void toString_nonEmptyTaskList_noTrailingNewLine() {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Todo("todo desc2"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        assertEquals("1. [T][✘] todo desc1\n" +
                "2. [T][✘] todo desc2\n" +
                "3. [D][✘] deadline desc1 (by: Dec 30 2020)\n" +
                "4. [E][✘] event desc1 (at: Dec 30 2020)", tasks.toString());
    }
}
