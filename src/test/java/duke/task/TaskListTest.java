package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidDateInputException;

public class TaskListTest {

    @Test
    public void addTask_taskListWithSomeItems_taskAddedAsLastItem() throws DukeException {
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
    public void updateTaskOfSameType_taskListWithSomeItems_success() throws DukeException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        Task taskToBeUpdated = new Todo("todo desc2");
        testInputTasks.add(taskToBeUpdated);
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);

        Task updatedTask = new Todo("todo desc3");

        assertEquals(taskToBeUpdated, tasks.updateTask(2, updatedTask));

        assertEquals(updatedTask, tasks.getTask(2));
    }

    @Test
    public void updateTaskWithDifferentType_taskListWithSomeItems_success() throws DukeException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        Task taskToBeUpdated = new Todo("todo desc2");
        testInputTasks.add(taskToBeUpdated);
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);

        Task updatedTask = new Event("event desc3", "2020-10-30");

        assertEquals(taskToBeUpdated, tasks.updateTask(2, updatedTask));

        assertEquals(updatedTask, tasks.getTask(2));
    }

    @Test
    public void updateTaskThatIsCompleted_taskListWithSomeItems_remainCompleted()
            throws DukeException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        Task taskToBeUpdated = new Todo("todo desc2");
        taskToBeUpdated.markAsDone();
        testInputTasks.add(taskToBeUpdated);
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);

        Task updatedTask = new Event("event desc3", "2020-10-30");

        assertEquals(taskToBeUpdated, tasks.updateTask(2, updatedTask));

        assertEquals(updatedTask, tasks.getTask(2));
        assertTrue(updatedTask.isDone());
    }

    @Test
    public void deleteTask_taskListWithSomeItems_success() throws DukeException {
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

        assertEquals(numItemsInitial - 1, numItemsAfter);
    }

    @Test
    public void size_emptyTaskList_zero() throws InvalidDateInputException {
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
    public void search_onEmptyTaskList_emptyTaskList() {
        TaskList tasks = new TaskList();
        TaskList filteredTasks = tasks.search("blahblah");
        assertEquals(0, filteredTasks.size());
    }

    @Test
    public void search_keywordIsSubstring_correctlyFilteredTaskList()
            throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo blahblah"));
        testInputTasks.add(new Deadline("deadline blah", "2020-12-30"));
        testInputTasks.add(new Event("event blah blah", "2020-12-30"));
        testInputTasks.add(new Todo("todo asdf"));

        TaskList tasks = new TaskList(testInputTasks);
        TaskList filteredTasks = tasks.search("blah");
        assertEquals(
                "1. [T][\u2718] todo blahblah\n2. [D][\u2718] deadline blah (by: Dec 30 2020)\n"
                        + "3. [E][\u2718] event blah blah (at: Dec 30 2020)",
                filteredTasks.toString());
    }

    @Test
    public void search_caseInsensitiveKeyword_correctlyFilteredTaskList()
            throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo blaH"));
        testInputTasks.add(new Deadline("deadline BLAH", "2020-12-30"));
        testInputTasks.add(new Event("event blah blah", "2020-12-30"));
        testInputTasks.add(new Todo("todo asdf"));

        TaskList tasks = new TaskList(testInputTasks);
        TaskList filteredTasks = tasks.search("BLAh");
        assertEquals("1. [T][\u2718] todo blaH\n2. [D][\u2718] deadline BLAH (by: Dec 30 2020)\n"
                + "3. [E][\u2718] event blah blah (at: Dec 30 2020)", filteredTasks.toString());
    }

    @Test
    public void toString_emptyTaskList_emptyString() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }

    @Test
    public void toString_nonEmptyTaskList_noTrailingNewLine() throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();

        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Todo("todo desc2"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        assertEquals("1. [T][\u2718] todo desc1\n" + "2. [T][\u2718] todo desc2\n"
                + "3. [D][\u2718] deadline " + "desc1 (by: Dec 30 2020)\n"
                + "4. [E][\u2718] event desc1 (at: Dec 30 2020)", tasks.toString());
    }
}
