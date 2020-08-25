import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.TaskList;
import duke.utility.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UiTest {
    private Ui ui;

    @BeforeEach
    public void init() {
        ui = new Ui();
    }

    @Test
    public void doneSuccessTest() {
        String taskName = "Sample task";
        Task toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        String toDoTaskExpected = "Sucessfully marked this task as done:\n"
                + "  " + "[T][O] Sample task";
        Assertions.assertEquals(toDoTaskExpected, ui.doneSuccess(toDoTask));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);

        Task deadlineTask = new DeadlineTask(taskName, date);
        deadlineTask.setStatusToDone();
        String deadlineTaskExpected = "Sucessfully marked this task as done:\n"
                + "  " + "[D][O] Sample task (by: Jan 01 2020 00:00)";
        Assertions.assertEquals(deadlineTaskExpected, ui.doneSuccess(deadlineTask));

        Task eventTask = new EventTask(taskName, date);
        eventTask.setStatusToDone();
        String eventTaskExpected = "Sucessfully marked this task as done:\n"
                + "  " + "[E][O] Sample task (at: Jan 01 2020 00:00)";
        Assertions.assertEquals(eventTaskExpected, ui.doneSuccess(eventTask));
    }

    @Test
    public void deleteSuccessTest() {
        String taskName = "Sample task";
        Task toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        String toDoTaskExpected = "Okay. I will delete this task:\n"
                + "  " + "[T][O] Sample task\n"
                + "Now you have 2 tasks in the list.";
        Assertions.assertEquals(toDoTaskExpected, ui.deleteSuccess(toDoTask, 2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);

        Task deadlineTask = new DeadlineTask(taskName, date);
        String deadlineTaskExpected = "Okay. I will delete this task:\n"
                + "  " + "[D][X] Sample task (by: Jan 01 2020 00:00)\n"
                + "Now you have 1 task in the list.";
        Assertions.assertEquals(deadlineTaskExpected, ui.deleteSuccess(deadlineTask, 1));

        Task eventTask = new EventTask(taskName, date);
        eventTask.setStatusToDone();
        String eventTaskExpected = "Okay. I will delete this task:\n"
                + "  " + "[E][O] Sample task (at: Jan 01 2020 00:00)\n"
                + "Now you have 3 tasks in the list.";
        Assertions.assertEquals(eventTaskExpected, ui.deleteSuccess(eventTask, 3));
    }

    @Test
    public void addSuccessTest() {
        String taskName = "Sample task";
        Task toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        String toDoTaskExpected = "Okay. I will add this task:\n"
                + "  " + "[T][O] Sample task\n"
                + "Now you have 2 tasks in the list.";
        Assertions.assertEquals(toDoTaskExpected, ui.addSuccess(toDoTask, 2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);

        Task deadlineTask = new DeadlineTask(taskName, date);
        String deadlineTaskExpected = "Okay. I will add this task:\n"
                + "  " + "[D][X] Sample task (by: Jan 01 2020 00:00)\n"
                + "Now you have 1 task in the list.";
        Assertions.assertEquals(deadlineTaskExpected, ui.addSuccess(deadlineTask, 1));

        Task eventTask = new EventTask(taskName, date);
        eventTask.setStatusToDone();
        String eventTaskExpected = "Okay. I will add this task:\n"
                + "  " + "[E][O] Sample task (at: Jan 01 2020 00:00)\n"
                + "Now you have 3 tasks in the list.";
        Assertions.assertEquals(eventTaskExpected, ui.addSuccess(eventTask, 3));
    }

    @Test
    public void showListTest() {
        TaskList taskList = new TaskList();

        String taskName = "Sample task";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);

        Task toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        Task deadlineTask = new DeadlineTask(taskName, date);
        Task eventTask = new EventTask(taskName, date);
        eventTask.setStatusToDone();

        taskList.addTask(toDoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);

        String showListBeforeExpected = "Here is the tasks in your list:\n" +
                "1. [T][O] Sample task\n" +
                "2. [D][X] Sample task (by: Jan 01 2020 00:00)\n" +
                "3. [E][O] Sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(showListBeforeExpected, ui.showList(taskList));

        taskList.addTask(new ToDoTask("Another sample task"));
        taskList.addTask(new EventTask("Another sample task", date));

        String showListAfterExpected = "Here is the tasks in your list:\n" +
                "1. [T][O] Sample task\n" +
                "2. [D][X] Sample task (by: Jan 01 2020 00:00)\n" +
                "3. [E][O] Sample task (at: Jan 01 2020 00:00)\n" +
                "4. [T][X] Another sample task\n" +
                "5. [E][X] Another sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(showListAfterExpected, ui.showList(taskList));
    }

    @Test
        public void eachTaskAfterTest() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDate dateTest = LocalDate.parse("2020-01-01");

            TaskList taskList = new TaskList();
            String taskName = "Sample task";

            LocalDateTime deadlineDate1 = LocalDateTime.parse("2020-05-05 0000", formatter);
            LocalDateTime deadlineDate2 = LocalDateTime.parse("2019-05-05 0000", formatter);

            DeadlineTask deadlineTask1 = new DeadlineTask(taskName, deadlineDate1);
            deadlineTask1.setStatusToDone();
            DeadlineTask deadlineTask2 = new DeadlineTask(taskName, deadlineDate2);

            taskList.addTask(deadlineTask1);
            taskList.addTask(deadlineTask2);

            LocalDateTime eventDate1 = LocalDateTime.parse("2020-10-10 0000", formatter);
            LocalDateTime eventDate2 = LocalDateTime.parse("2019-10-10 0000", formatter);

            EventTask eventTask1 = new EventTask(taskName, eventDate1);
            EventTask eventTask2 = new EventTask(taskName, eventDate2);
            eventTask2.setStatusToDone();

            taskList.addTask(eventTask1);
            taskList.addTask(eventTask2);

            String expectedOutput = "Here is the tasks after Jan 01 2020:\n" +
                    "1. [D][O] Sample task (by: May 05 2020 00:00)\n" +
                    "2. [E][X] Sample task (at: Oct 10 2020 00:00)";

            Assertions.assertEquals(expectedOutput, ui.eachTaskAfter(dateTest, taskList.getTasks()));
    }

    @Test
    public void eachTaskBeforeTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDate dateTest = LocalDate.parse("2020-01-01");

        TaskList taskList = new TaskList();
        String taskName = "Sample task";

        LocalDateTime deadlineDate1 = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime deadlineDate2 = LocalDateTime.parse("2019-05-05 0000", formatter);

        DeadlineTask deadlineTask1 = new DeadlineTask(taskName, deadlineDate1);
        deadlineTask1.setStatusToDone();
        DeadlineTask deadlineTask2 = new DeadlineTask(taskName, deadlineDate2);

        taskList.addTask(deadlineTask1);
        taskList.addTask(deadlineTask2);

        LocalDateTime eventDate1 = LocalDateTime.parse("2020-10-10 0000", formatter);
        LocalDateTime eventDate2 = LocalDateTime.parse("2019-10-10 0000", formatter);

        EventTask eventTask1 = new EventTask(taskName, eventDate1);
        EventTask eventTask2 = new EventTask(taskName, eventDate2);
        eventTask2.setStatusToDone();

        taskList.addTask(eventTask1);
        taskList.addTask(eventTask2);

        String expectedOutput = "Here is the tasks before Jan 01 2020:\n" +
                "1. [D][X] Sample task (by: May 05 2019 00:00)\n" +
                "2. [E][O] Sample task (at: Oct 10 2019 00:00)";

        Assertions.assertEquals(expectedOutput, ui.eachTaskBefore(dateTest, taskList.getTasks()));
    }

    @Test
    public void createLineTest() {
        String shortMessage = "This is a short message.";
        String expectedShortMessage =
                "===========================================================================\n" +
                "  This is a short message.\n" +
                "===========================================================================";
        Assertions.assertEquals(expectedShortMessage, ui.createLine(shortMessage));

        String longMessage = "This is a really " +
                "looooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnggggggggggggg message";
        String expectedLongMessage =
                "=====================================================================================================\n" +
                "  This is a really looooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnggggggggggggg message\n" +
                "=====================================================================================================";
        Assertions.assertEquals(expectedLongMessage, ui.createLine(longMessage));
    }
}
