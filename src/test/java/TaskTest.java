import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskTest {
    @Test
    public void taskMethodsTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDate = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime eventDate = LocalDateTime.parse("2020-10-10 0000", formatter);
        String taskName = "Sample Task";

        Task toDoTask = new ToDoTask(taskName);
        Task deadlineTask = new DeadlineTask(taskName, deadlineDate);
        Task eventTask = new EventTask(taskName, eventDate);

        Assertions.assertEquals(taskName, toDoTask.getTaskName());
        Assertions.assertEquals(taskName, deadlineTask.getTaskName());
        Assertions.assertEquals(taskName, eventTask.getTaskName());

        Assertions.assertFalse(toDoTask.isDone());
        Assertions.assertFalse(deadlineTask.isDone());
        Assertions.assertFalse(eventTask.isDone());

        String toDoTaskExpectedNotDone = "[T][X] Sample Task";
        String deadlineTaskExpectedNotDone = "[D][X] Sample Task (by: May 05 2020 00:00)";
        String eventTaskExpectedNotDone = "[E][X] Sample Task (at: Oct 10 2020 00:00)";

        Assertions.assertEquals(toDoTaskExpectedNotDone, toDoTask.toString());
        Assertions.assertEquals(deadlineTaskExpectedNotDone, deadlineTask.toString());
        Assertions.assertEquals(eventTaskExpectedNotDone, eventTask.toString());

        toDoTask.setStatusToDone();
        deadlineTask.setStatusToDone();
        eventTask.setStatusToDone();

        Assertions.assertTrue(toDoTask.isDone());
        Assertions.assertTrue(deadlineTask.isDone());
        Assertions.assertTrue(eventTask.isDone());

        String toDoTaskExpectedDone = "[T][O] Sample Task";
        String deadlineTaskExpectedDone = "[D][O] Sample Task (by: May 05 2020 00:00)";
        String eventTaskExpectedDone = "[E][O] Sample Task (at: Oct 10 2020 00:00)";

        Assertions.assertEquals(toDoTaskExpectedDone, toDoTask.toString());
        Assertions.assertEquals(deadlineTaskExpectedDone, deadlineTask.toString());
        Assertions.assertEquals(eventTaskExpectedDone, eventTask.toString());
    }

    @Test
    public void deadlineTaskTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDateTask = LocalDateTime.parse("2020-05-05 0000", formatter);
        String taskName = "Sample Task";
        DeadlineTask deadlineTask = new DeadlineTask(taskName, deadlineDateTask);

        LocalDateTime deadlineDateCorrect = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime deadlineDateWrong = LocalDateTime.parse("2020-10-05 0000", formatter);

        Assertions.assertTrue(deadlineTask.getDate().equals(deadlineDateCorrect));
        Assertions.assertFalse(deadlineTask.getDate().equals(deadlineDateWrong));

        String dateExpected = "2020-05-05 0000";
        Assertions.assertEquals(dateExpected, deadlineTask.getDateString());
    }

    @Test
    public void eventTaskTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime eventDateTask = LocalDateTime.parse("2020-05-05 0000", formatter);
        String taskName = "Sample Task";
        EventTask eventTask = new EventTask(taskName, eventDateTask);

        LocalDateTime eventDateCorrect = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime eventDateWrong = LocalDateTime.parse("2020-10-05 0000", formatter);

        Assertions.assertTrue(eventTask.getDate().equals(eventDateCorrect));
        Assertions.assertFalse(eventTask.getDate().equals(eventDateWrong));

        String dateExpected = "2020-05-05 0000";
        Assertions.assertEquals(dateExpected, eventTask.getDateString());
    }
}
