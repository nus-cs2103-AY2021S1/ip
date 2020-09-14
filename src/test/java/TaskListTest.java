import luke.TaskList;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static final List<Task> DUMMY_TASKS = Arrays.asList(
            new Todo("do 1"),
            new Deadline("do 2", LocalDateTime.of(2020, 2, 22, 22, 22)),
            new Event("do 3", "March 3rd"),
            new Todo("do 4"),
            new Deadline("do 5", LocalDateTime.of(2020, 5, 5, 5, 55)),
            new Event("do 6", "June 6th"));

    @Test
    @DisplayName("Add task")
    public void addTask_newTask_success() {
        List<Task> test = new ArrayList<>(DUMMY_TASKS);
        List<Task> expected = new ArrayList<>(DUMMY_TASKS);

        Task newTask = new Todo("do 7");

        TaskList taskList = new TaskList(test);
        taskList.add(newTask);
        expected.add(newTask);

        List<Task> actualResult = taskList.getTasks();

        assertArrayEquals(expected.toArray(), actualResult.toArray());
    }
}
