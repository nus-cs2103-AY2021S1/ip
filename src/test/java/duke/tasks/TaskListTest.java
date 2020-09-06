package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeInvalidKeywordException;
import duke.exceptions.DukeInvalidScheduleInputException;
import duke.exceptions.DukeInvalidTaskDescriptionException;
import duke.exceptions.DukeInvalidTaskTimeException;

import duke.messages.Message;

public class TaskListTest {

    private TaskList tasks = new TaskList();

    private Task toDo = new ToDo("test 1");
    private Task event = new Event("test 2", "23-02-2020 23:00");
    private Task deadline = new Deadline("test 3", "01-01-2020 00:00");

    private Task event2 = new Event("test 4", "23-02-2020 23:59");

    private TaskList createTaskList() {
        List<Task> sample = new ArrayList<>();
        sample.add(toDo);
        sample.add(event);
        sample.add(deadline);
        sample.add(event2);
        return new TaskList(sample);
    }

    @Test
    public void addToDo_validToDo_success()
            throws DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException {
        assertEquals(toDo.toString(),
                tasks.addTask("todo", "todo test 1").toString());
    }

    @Test
    public void addToDo_erroneousDescription_exceptionThrown() throws DukeInvalidTaskTimeException {
        try {
            assertEquals(toDo.toString(),
                    tasks.addTask("todo", "todo").toString());
        } catch (DukeInvalidTaskDescriptionException e) {
            assertEquals(Message.ERROR_INVALID_TASK_DESCRIPTION, e.toString());
        }
    }

    @Test
    public void addEvent_validEvent_success()
            throws DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException {
        assertEquals(event.toString(),
                tasks.addTask("event", "event test 2 /at 23-02-2020 23:00").toString());
    }

    @Test
    public void addEvent_erroneousTime_exceptionThrown() throws DukeInvalidTaskDescriptionException {
        try {
            assertEquals(event.toString(),
                    tasks.addTask("event", "event /at blah").toString());
        } catch (DukeInvalidTaskTimeException e) {
            assertEquals(String.format("%s\n%s", Message.ERROR_EVENT_TIME, Message.ERROR_TIME_FORMATTING),
                    e.toString());
        }
    }

    @Test
    public void addDeadline_validDeadline_success()
            throws DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException {
        assertEquals(deadline.toString(),
                tasks.addTask("deadline", "deadline test 3 /by 01-01-2020 00:00").toString());
    }

    @Test
    public void addDeadline_erroneousTime_exceptionThrown() throws DukeInvalidTaskDescriptionException {
        try {
            assertEquals(deadline.toString(),
                    tasks.addTask("deadline", "deadline /by blah").toString());
        } catch (DukeInvalidTaskTimeException e) {
            assertEquals(String.format("%s\n%s", Message.ERROR_DEADLINE_TIME, Message.ERROR_TIME_FORMATTING),
                    e.toString());
        }
    }

    @Test
    public void completeTask_validInput_success() throws DukeInvalidIndexException {
        toDo.markAsDone();
        assertEquals(toDo.toString(),
                createTaskList().completeTask("done 1").toString());
    }

    @Test
    public void completeTask_erroneousInput_exceptionThrown() {
        try {
            toDo.markAsDone();
            assertEquals(toDo.toString(),
                    createTaskList().completeTask("done 1000").toString());
        } catch (DukeInvalidIndexException e) {
            assertEquals(Message.ERROR_INVALID_INDEX, e.toString());
        }
    }

    @Test
    public void deleteTask_validInput_success() throws DukeInvalidIndexException {
        assertEquals(toDo.toString(),
                createTaskList().deleteTask("delete 1").toString());
    }

    @Test
    public void deleteTask_erroneousInput_exceptionThrown() {
        try {
            assertEquals(toDo.toString(),
                    createTaskList().deleteTask("delete 1000").toString());
        } catch (DukeInvalidIndexException e) {
            assertEquals(Message.ERROR_INVALID_INDEX, e.toString());
        }
    }

    @Test
    public void findTask_validInput_success() throws DukeInvalidKeywordException {
        assertEquals(event.toString(),
                createTaskList().findTasks("find test").get(1).toString());
    }

    @Test
    public void findTask_erroneousInput_exceptionThrown() {
        try {
            assertEquals(event.toString(),
                    createTaskList().findTasks("find").toString());
        } catch (DukeInvalidKeywordException e) {
            assertEquals(Message.ERROR_INVALID_KEYWORD, e.toString());
        }
    }

    @Test
    public void schedule_validInput_success() throws DukeInvalidScheduleInputException {
        assertEquals(event2.toString(),
                createTaskList().findScheduledTasks("schedule 23-02-2020").get(1).toString());
    }

    @Test
    public void schedule_erroneousInput_exceptionThrown() {
        try {
            assertEquals(event2.toString(),
                    createTaskList().findScheduledTasks("schedule hello").get(1).toString());
        } catch (DukeInvalidScheduleInputException e) {
            assertEquals(String.format("%s\n%s", Message.ERROR_SCHEDULE_INPUT, Message.ERROR_DATE_FORMATTING),
                    e.toString());
        }
    }

}
