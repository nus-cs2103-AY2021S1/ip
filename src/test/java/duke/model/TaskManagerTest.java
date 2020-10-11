package duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ToDo;

public class TaskManagerTest {
    private TaskManager tm = new TaskManager();

    @Test
    public void addTask_newDeadline() {
        assertEquals(0, tm.getTaskList().size());
        LocalDate dlDate = LocalDate.parse("2020-09-01");
        Deadline deadline = new Deadline("Test", dlDate);
        tm.addTask(deadline);
        assertEquals(1, tm.getTaskList().size());
    }

    @Test
    public void addTask_newEvent() {
        assertEquals(0, tm.getTaskList().size());
        LocalDate eventDate = LocalDate.parse("2020-09-01");
        Event event = new Event("Test", eventDate);
        tm.addTask(event);
        assertEquals(1, tm.getTaskList().size());
    }

    @Test
    public void addTask_newTodo() {
        assertEquals(0, tm.getTaskList().size());
        ToDo todo = new ToDo("Test");
        tm.addTask(todo);
        assertEquals(1, tm.getTaskList().size());
    }

    @Test
    public void markTaskDone_validIndex() throws DukeException {
        assertEquals(0, tm.getTaskList().size());
        ToDo todo = new ToDo("Test");
        tm.addTask(todo);
        assertEquals(1, tm.getTaskList().size());
        tm.markTaskDone(0);
        assertEquals(true, tm.getTask(0).isDone());
    }

    @Test
    public void markTaskDone_invalidIndex_exceptionThrown() {
        assertEquals(0, tm.getTaskList().size());
        ToDo todo = new ToDo("Test");
        tm.addTask(todo);
        assertEquals(1, tm.getTaskList().size());
        Exception exception1 = assertThrows(DukeException.class, () -> tm.markTaskDone(1));
        Exception exception2 = assertThrows(DukeException.class, () -> tm.markTaskDone(-1));
        assertEquals(exception1.getMessage(), "Invalid index!");
        assertEquals(exception2.getMessage(), "Invalid index!");
    }

    @Test
    public void deleteTask_validIndex() throws DukeException {
        assertEquals(0, tm.getTaskList().size());
        ToDo todo = new ToDo("Test");
        tm.addTask(todo);
        assertEquals(1, tm.getTaskList().size());
        tm.deleteTask(0);
        assertEquals(0, tm.getTaskList().size());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        assertEquals(0, tm.getTaskList().size());
        ToDo todo = new ToDo("Test");
        tm.addTask(todo);
        assertEquals(1, tm.getTaskList().size());
        Exception exception1 = assertThrows(DukeException.class, () -> tm.deleteTask(1));
        Exception exception2 = assertThrows(DukeException.class, () -> tm.deleteTask(-1));
        assertEquals(exception1.getMessage(), "Invalid index!");
        assertEquals(exception2.getMessage(), "Invalid index!");
    }


}
