import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

public class TaskTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private ToDoTask toDoTask;
    private DeadlineTask deadlineTask;
    private EventTask eventTask;

    @BeforeEach
    public void init() {
        LocalDateTime dateTest = LocalDateTime.parse("2020-01-01 0000", formatter);
        String taskName = "Sample task";
        this.toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        this.deadlineTask = new DeadlineTask(taskName, dateTest);
        deadlineTask.setStatusToDone();
        this.eventTask = new EventTask(taskName, dateTest);
    }
    @Test
    public void taskMethodsTest() {
        Assertions.assertEquals("Sample task", toDoTask.getTaskName());
        Assertions.assertEquals("Sample task", deadlineTask.getTaskName());
        Assertions.assertEquals("Sample task", eventTask.getTaskName());

        Assertions.assertTrue(toDoTask.isDone());
        Assertions.assertTrue(deadlineTask.isDone());
        Assertions.assertFalse(eventTask.isDone());

        String toDoTaskExpected = "[T][O] Sample task";
        String deadlineTaskExpected = "[D][O] Sample task (by: Jan 01 2020 00:00)";
        String eventTaskExpected = "[E][X] Sample task (at: Jan 01 2020 00:00)";

        Assertions.assertEquals(toDoTaskExpected, toDoTask.toString());
        Assertions.assertEquals(deadlineTaskExpected, deadlineTask.toString());
        Assertions.assertEquals(eventTaskExpected, eventTask.toString());
    }

    @Test
    public void deadlineTaskTest() {
        LocalDateTime testDate = LocalDateTime.parse("2020-01-01 0000", formatter);
        String dateExpected = "2020-01-01 0000";

        Assertions.assertTrue(deadlineTask.getDate().equals(testDate));
        Assertions.assertEquals(dateExpected, deadlineTask.getDateString());
    }

    @Test
    public void eventTaskTest() {
        LocalDateTime testDate = LocalDateTime.parse("2020-01-01 0000", formatter);
        String dateExpected = "2020-01-01 0000";

        Assertions.assertTrue(eventTask.getDate().equals(testDate));
        Assertions.assertEquals(dateExpected, eventTask.getDateString());
    }
}
