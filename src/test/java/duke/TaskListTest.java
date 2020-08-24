package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TaskListTest {
    Todo todo = new Todo("Get bread");
    Deadline deadline = new Deadline("Finish project", LocalDate.parse("2012-01-23"));
    Event event = new Event("Birthday", LocalDate.parse("2020-05-30"));

    @Test
    public void addTask_addSomeTasks_taskListWithAllTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        assertEquals("\n\t1.[T]✘ Get bread\n" +
                "\t2.[D]✘ Finish project (by: Jan 23 2012)\n" +
                "\t3.[E]✘ Birthday (at: May 30 2020)", taskList.toString());
    }

    @Test
    public void deleteTask_deleteOneTask_correctTaskDeleted() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.deleteTask(2);
        assertEquals("\n\t1.[T]✘ Get bread\n" +
                "\t2.[E]✘ Birthday (at: May 30 2020)", taskList.toString());
    }

    @Test
    public void markAsDone_markDoneOneTask_correctTaskMarkDone() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markTaskAsDone(3);
        assertEquals("\n\t1.[T]✘ Get bread\n" +
                "\t2.[D]✘ Finish project (by: Jan 23 2012)\n" +
                "\t3.[E]✓ Birthday (at: May 30 2020)", taskList.toString());
    }

    @Test
    public void getTaskListOnDate_oneTaskOnDate_correctTaskListOnDate() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        TaskList newTL = taskList.getTaskListOnDate(LocalDate.parse("2012-01-23"));
        assertEquals("\n\t1.[D]✘ Finish project (by: Jan 23 2012)", newTL.toString());
    }
}
