package duke;

import duke.exception.*;
import duke.task.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList tasks = new TaskList();

    Task toDo = new ToDo("test 1");
    Task event = new Event("test 2", "23-02-2020 23:00");
    Task deadline = new Deadline("test 3", "01-01-2020 00:00");

    private TaskList createTaskList() {
        List<Task> sample = new ArrayList<>();
        sample.add(toDo);
        sample.add(event);
        sample.add(deadline);
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
    public void addToDo_erroneousToDo_exceptionThrown() throws DukeInvalidTaskTimeException {
        try {
            assertEquals(toDo.toString(),
                    tasks.addTask("todo", "todo").toString());
        } catch (DukeInvalidTaskDescriptionException e) {
            assertEquals("ERROR: The description of a task cannot be empty!", e.toString());
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
    public void addEvent_erroneousEvent_exceptionThrown() throws DukeInvalidTaskDescriptionException {
        try {
            assertEquals(event.toString(),
                    tasks.addTask("event", "event /at blah").toString());
        } catch (DukeInvalidTaskTimeException e) {
            assertEquals("ERROR: Usage: <event> <description> /at <time>\n"
                    + "    Time formatting: dd-MM-yyyy HH:mm", e.toString());
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
    public void addDeadline_erroneousEvent_exceptionThrown() throws DukeInvalidTaskDescriptionException {
        try {
            assertEquals(deadline.toString(),
                    tasks.addTask("deadline", "deadline /by blah").toString());
        } catch (DukeInvalidTaskTimeException e) {
            assertEquals("ERROR: Usage: <deadline> <description> /by <time>\n"
                    + "    Time formatting: dd-MM-yyyy HH:mm", e.toString());
        }
    }

    @Test
    public void completeTask_validInput_success() throws DukeInvalidListNumberInputException {
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
        } catch (DukeInvalidListNumberInputException e) {
            assertEquals("ERROR: Invalid list number input!", e.toString());
        }
    }

    @Test
    public void deleteTask_validInput_success() throws DukeInvalidListNumberInputException {
        assertEquals(toDo.toString(),
                createTaskList().deleteTask("delete 1").toString());
    }

    @Test
    public void deleteTask_erroneousInput_exceptionThrown() {
        try {
            assertEquals(toDo.toString(),
                    createTaskList().deleteTask("delete 1000").toString());
        } catch (DukeInvalidListNumberInputException e) {
            assertEquals("ERROR: Invalid list number input!", e.toString());
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
            assertEquals("ERROR: The keyword cannot be empty!", e.toString());
        }
    }

}
