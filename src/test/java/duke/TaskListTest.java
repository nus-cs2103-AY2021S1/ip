package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    ToDo todo = new ToDo("homework");
    Deadline deadline = new Deadline("assignment", LocalDate.parse("2020-09-05"));
    Event event = new Event("party", LocalDate.parse("2020-09-04"));

    @Test
    public void addTask_tasksAddedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        String expected = "\n1.[T][0] homework" +
                "\n2.[D][0] assignment (by: 5 Sep 2020)" +
                "\n3.[E][0] party (at: 4 Sep 2020)";
        assertEquals(expected, tasks.toString());
    }

    @Test
    public void deleteTask_deleteSecondTask_taskDeletedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.deleteTask(2);
        String expected = "\n1.[T][0] homework" +
                "\n2.[E][0] party (at: 4 Sep 2020)";
        assertEquals(expected, tasks.toString());
    }

    @Test
    public void getTasks_tasksRetrievedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        List<Task> actualTasks = tasks.getTasks();

        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(todo);
        expectedTasks.add(deadline);
        expectedTasks.add(event);

        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    public void getNumOfTasks_numOfTasksRetrievedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        int numOfTasks = tasks.getNumOfTasks();
        assertEquals(3, numOfTasks);
    }

    @Test
    public void getTask_taskRetrievedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        Task task = tasks.getTask(1);
        assertEquals(todo, task);
    }

    @Test
    public void markAsDone_taskCompletedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.markAsDone(3);
        assertEquals("\n1.[T][0] homework" +
                "\n2.[D][0] assignment (by: 5 Sep 2020)" +
                "\n3.[E][1] party (at: 4 Sep 2020)", tasks.toString());
    }
}
