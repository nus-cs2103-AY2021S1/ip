package viscount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static final List<Task> DEFAULT_TASKS = Arrays.asList(
            new Todo("t1", false),
            new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
            new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)),
            new Event("t4", true, LocalDateTime.of(2020, 8, 24, 11, 0)),
            new Event("t5", false, LocalDateTime.of(2020, 8, 27, 12, 0)));
    
    @Test
    @DisplayName("Add task")
    public void addTask_newTask_success() {
        List<Task> testedCopy = new ArrayList<>(DEFAULT_TASKS);
        List<Task> expectedResult = new ArrayList<>(DEFAULT_TASKS);
        
        Task newTask = new Todo("t6", false);
        
        TaskList taskList = new TaskList(testedCopy);
        taskList.add(newTask);
        expectedResult.add(newTask);
        
        List<Task> actualResult = taskList.getTasks();
        
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    @DisplayName("Mark task as done with valid input")
    public void markDone_validInput_success(int taskIndex) throws ViscountIndexOutOfBoundsException {
        List<Task> testedCopy = new ArrayList<>(DEFAULT_TASKS);
        List<Task> expectedResult = new ArrayList<>(DEFAULT_TASKS);

        TaskList taskList = new TaskList(testedCopy);
        taskList.markDone(taskIndex);
        
        expectedResult.get(taskIndex).setDone(true);

        List<Task> actualResult = taskList.getTasks();

        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    @DisplayName("Remove task with valid input")
    public void removeTask_validInput_success(int taskIndex) throws ViscountIndexOutOfBoundsException {
        List<Task> testedCopy = new ArrayList<>(DEFAULT_TASKS);
        List<Task> expectedResult = new ArrayList<>(DEFAULT_TASKS);

        TaskList taskList = new TaskList(testedCopy);
        taskList.remove(taskIndex);

        expectedResult.remove(taskIndex);

        List<Task> actualResult = taskList.getTasks();

        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    @DisplayName("Get task with valid input")
    public void getTask_validInput_success(int taskIndex) throws ViscountIndexOutOfBoundsException {
        List<Task> testedCopy = new ArrayList<>(DEFAULT_TASKS);
        List<Task> expectedCopy = new ArrayList<>(DEFAULT_TASKS);

        TaskList taskList = new TaskList(testedCopy);

        Task expectedResult = expectedCopy.get(taskIndex);
        Task actualResult = taskList.getTask(taskIndex);

        assertEquals(expectedResult, actualResult);
    }
}
