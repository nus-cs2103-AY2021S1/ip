import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.TaskList;
import duke.utility.Ui;

public class UiTest {
    private Ui ui;
    private Task toDoTask;
    private Task deadlineTask;
    private Task eventTask;

    @BeforeEach
    public void init() {
        ui = new Ui();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTest = LocalDateTime.parse("2020-01-01 0000", formatter);
        String taskName = "Sample task";
        this.toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        this.deadlineTask = new DeadlineTask(taskName, dateTest);
        deadlineTask.setStatusToDone();
        this.eventTask = new EventTask(taskName, dateTest);
    }

    @Test
    public void doneSuccessTest() {
        String toDoTaskExpected = "Successfully marked this task as done:\n"
                + "  " + "[T][O] Sample task";
        Assertions.assertEquals(toDoTaskExpected, ui.doneSuccess(toDoTask));
    }

    @Test
    public void deleteSuccessTest() {
        String deadlineTaskExpected = "Okay. I will delete this task:\n"
                + "  " + "[D][X] Sample task (by: Jan 01 2020 00:00)\n"
                + "Now you have 1 task in the list.";
        Assertions.assertEquals(deadlineTaskExpected, ui.deleteSuccess(deadlineTask, 1));
    }

    @Test
    public void addSuccessTest() {
        String eventTaskExpected = "Okay. I will add this task:\n"
                + "  " + "[E][O] Sample task (at: Jan 01 2020 00:00)\n"
                + "Now you have 3 tasks in the list.";
        Assertions.assertEquals(eventTaskExpected, ui.addSuccess(eventTask, 3));
    }

    @Test
    public void showListTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(toDoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);

        String showListBeforeExpected = "Here is the tasks in your list:\n"
                + "1. [T][O] Sample task\n"
                + "2. [D][O] Sample task (by: Jan 01 2020 00:00)\n"
                + "3. [E][X] Sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(showListBeforeExpected, ui.showList(taskList));
    }

    @Test
    public void showTaskAfterTest() {
        TaskList taskList = new TaskList();
        LocalDate dateTest = LocalDate.parse("2019-01-01 0000");

        taskList.addTask(toDoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);

        String expectedOutput = "Here is the tasks after Jan 01 2019:\n"
                + "1. [D][O] Sample task (by: Jan 01 2020 00:00)\n"
                + "2. [E][X] Sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(expectedOutput, ui.showTaskAfter(dateTest, taskList));
    }

    @Test
    public void showTaskBeforeTest() {
        TaskList taskList = new TaskList();
        LocalDate dateTest = LocalDate.parse("2021-01-01 0000");

        taskList.addTask(toDoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);

        String expectedOutput = "Here is the tasks before Jan 01 2021:\n"
                + "1. [D][X] Sample task (by: Jan 01 2020 00:00)\n"
                + "2. [E][O] Sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(expectedOutput, ui.showTaskBefore(dateTest, taskList));
    }
}
