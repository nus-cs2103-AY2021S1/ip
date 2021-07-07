package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class TaskListTest {
    private final ToDo todo = new ToDo("wakeup");
    private final Deadline deadline = new Deadline("project", LocalDate.parse("2020-09-01"));
    private final Event event = new Event("holiday", LocalDate.parse("2020-12-12"));

    @Test
    public void markAsDone_doneSecondTask_correctTaskDone() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.markAsDone(2);
        assertEquals("\n\t1.[T][✗] wakeup"
                + "\n\t2.[D][✓] project (by: 1 Sep 2020)"
                + "\n\t3.[E][✗] holiday (at: 12 Dec 2020)", tasks.toString());
    }

    @Test
    public void deleteTask_deleteThirdTask_correctTaskDeleted() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.deleteTask(3);
        assertEquals("\n\t1.[T][✗] wakeup"
                + "\n\t2.[D][✗] project (by: 1 Sep 2020)", tasks.toString());
    }

    @Test
    public void getNumOfTasks_numOfTasks_correctNumOfTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        int numOfTasks = tasks.getNumOfTasks();
        assertEquals(3, numOfTasks);
    }
}
