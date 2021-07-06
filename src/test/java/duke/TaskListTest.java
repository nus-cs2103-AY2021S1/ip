package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class TaskListTest {
    private Todo todo = new Todo("Get bread");
    private Deadline deadline = new Deadline("Finish project", LocalDate.parse("2012-01-23"), null);
    private Event event = new Event("Birthday", LocalDate.parse("2020-05-30"), LocalTime.parse("20:15"));

    @Test
    public void addTask_addSomeTasks_taskListWithAllTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        assertEquals("\t1.[T]\u2718 Get bread\n"
                + "\t2.[D]\u2718 Finish project (by: Jan 23 2012)\n"
                + "\t3.[E]\u2718 Birthday (at: May 30 2020 20:15)\n", taskList.toString());
    }

    @Test
    public void deleteTask_deleteOneTask_correctTaskDeleted() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.deleteTask(2);
        assertEquals("\t1.[T]\u2718 Get bread\n"
                + "\t2.[E]\u2718 Birthday (at: May 30 2020 20:15)\n", taskList.toString());
    }

    @Test
    public void markAsDone_markDoneOneTask_correctTaskMarkDone() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markTaskAsDone(3);
        assertEquals("\t1.[T]\u2718 Get bread\n"
                + "\t2.[D]\u2718 Finish project (by: Jan 23 2012)\n"
                + "\t3.[E]\u2713 Birthday (at: May 30 2020 20:15)\n", taskList.toString());
    }

    @Test
    public void getTaskListOnDate_oneTaskOnDate_correctTaskListOnDate() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        TaskList newTL = null;
        try {
            newTL = taskList.getTaskListOnDate(LocalDate.parse("2012-01-23"));
        } catch (DukeException e) {
            throw new AssertionError();
        }
        assertEquals("\t1.[D]\u2718 Finish project (by: Jan 23 2012)\n", newTL.toString());
    }
}
