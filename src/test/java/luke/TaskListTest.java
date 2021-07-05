package luke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TaskListTest {
    //    I have referred to viscount-master for this part of the code
    private static final List<Task> DUMMY_TASKS = Arrays.asList(
            new Todo("do 1"),
            new Deadline("do 2", LocalDate.of(2020, 2, 22)),
            new Event("do 3", LocalDate.of(2020, 3, 3)),
            new Todo("do 4"),
            new Deadline("do 5", LocalDate.of(2020, 5, 5)),
            new Event("do 6", LocalDate.of(2020, 6, 6)));

    //    I have referred to viscount-master for this part of the code
    @Test
    public void addTask_newTask_tasklistUpdated() {
        List<Task> test = new ArrayList<>(DUMMY_TASKS);
        List<Task> expected = new ArrayList<>(DUMMY_TASKS);

        Task newTask = new Todo("do 7");

        TaskList taskList = new TaskList(test);
        taskList.add(newTask);
        expected.add(newTask);

        List<Task> actual = taskList.getTasks();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void deleteTask_taskNumber_tasklistUpdated() {
        List<Task> test = new ArrayList<>(DUMMY_TASKS);
        List<Task> expected = new ArrayList<>(DUMMY_TASKS);

        int taskNumber = 3;

        TaskList taskList = new TaskList(test);
        taskList.deleteTask(taskNumber);
        expected.remove(taskNumber - 1);

        List<Task> actual = taskList.getTasks();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}
