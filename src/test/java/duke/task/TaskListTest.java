package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.command.exception.DukeInvalidIndexException;
import duke.response.Response;

/**
 * Represents a test class for TaskList.
 */
public class TaskListTest {
    private TaskList taskList = new TaskList();

    private Task todo = new Todo("task 1");
    private Task deadline = new Deadline("task 2", LocalDate.of(2020, 8, 24), LocalTime.of(8, 24));
    private Task event = new Event("task 3", LocalDate.of(2020, 9, 9), LocalTime.of(9, 9));

    @Test
    public void addTodo_validTodo_success() {
        assertEquals(todo, taskList.addTask(todo));
    }

    @Test
    public void addDeadline_validDeadline_success() {
        assertEquals(deadline, taskList.addTask(deadline));
    }

    @Test
    public void addEvent_validEvent_success() {
        assertEquals(event, taskList.addTask(event));
    }

    @Test
    public void deleteTask_validIndex_success() throws DukeInvalidIndexException {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        assertEquals(todo, tl.deleteTask(0));
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        try {
            assertEquals(tl.deleteTask(1), todo);
        } catch (DukeInvalidIndexException e) {
            assertEquals("OOPS!!! There is no such task.", e.getMessage());
        }
    }

    @Test
    public void markDone_validIndex_success() throws DukeInvalidIndexException {
        TaskList tl = new TaskList();
        Task markDoneTodo = new Todo("markDone test");
        tl.addTask(markDoneTodo);
        markDoneTodo.markAsDone();
        assertEquals(markDoneTodo, tl.markDone(0));
    }

    @Test
    public void markDone_invalidIndex_exceptionThrown() {
        TaskList tl = new TaskList();
        Task markDoneTodo = new Todo("markDone test");
        tl.addTask(markDoneTodo);
        markDoneTodo.markAsDone();
        try {
            assertEquals(markDoneTodo, tl.markDone(1));
        } catch (DukeInvalidIndexException e) {
            assertEquals("OOPS!!! There is no such task.", e.getMessage());
        }
    }

    @Test
    public void getListAsStringFromList_emptyList_success() {
        List<Task> list = new ArrayList<>();
        assertEquals("Alternative String",
                TaskList.getListAsStringFromList(list, "Alternative String"));
    }

    @Test
    public void getListAsStringFromList_validList_success() {
        List<Task> list = new ArrayList<>();
        list.add(todo);
        String expectedResult = Response.TWO_INDENT + "1." + todo.toString();
        assertEquals(expectedResult,
                TaskList.getListAsStringFromList(list, "Alternative String"));
    }

    @Test
    public void getListAsStringFromList_validLongerList_success() {
        List<Task> list = new ArrayList<>();
        list.add(todo);
        list.add(deadline);
        String expectedResult = Response.TWO_INDENT + "1." + todo.toString() + "\n"
                + Response.TWO_INDENT + "2." + deadline.toString();
        assertEquals(expectedResult, TaskList.getListAsStringFromList(list, "Alternative"));
    }

    @Test
    public void getListAsString_emptyTaskList_success() {
        TaskList tl = new TaskList();
        assertEquals("There is nothing in the list!", tl.getListAsString());
    }

    @Test
    public void getListAsString_validTaskList_success() {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        String expectedResult = Response.TWO_INDENT + "1." + todo.toString();
        assertEquals(expectedResult, tl.getListAsString());
    }

    @Test
    public void getListAsString_validLongerTaskList_success() {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        tl.addTask(deadline);
        assertEquals(Response.TWO_INDENT + "1." + todo.toString()
                + "\n" + Response.TWO_INDENT
                + "2." + deadline.toString(), tl.getListAsString());
    }

    @Test
    public void getSize_emptyTaskList_success() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.getSize());
    }

    @Test
    public void getSize_validTaskList_success() {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        tl.addTask(deadline);
        tl.addTask(event);
        assertEquals(3, tl.getSize());
    }

    @Test
    public void get_validIndex_success() throws DukeInvalidIndexException {
        TaskList tl = new TaskList();
        tl.addTask(todo);
        assertEquals(todo, tl.get(0));
    }

    @Test
    public void get_invalidIndex_exceptionThrown() {
        TaskList tl = new TaskList();
        try {
            assertEquals(todo, tl.get(0));
        } catch (DukeInvalidIndexException e) {
            assertEquals("OOPS!!! There is no such task.", e.getMessage());
        }
    }

    @Test
    public void getTaskOnDate_validDate_success() {
        TaskList tl = new TaskList();
        tl.addTask(deadline);
        String expectedResult = Response.TWO_INDENT + "1." + deadline.toString();
        assertEquals(expectedResult,
                tl.getTasksOnDate(LocalDate.of(2020, 8, 24)));
    }

    @Test
    public void getTaskOnDate_emptyList_success() {
        TaskList tl = new TaskList();
        assertEquals("There are no tasks on this date!",
                tl.getTasksOnDate(LocalDate.of(2020, 1, 1)));
    }

    @Test
    public void getTaskWithKeyword_validKeyword_success() {
        TaskList tl = new TaskList();
        tl.addTask(deadline);
        String expectedResult = Response.TWO_INDENT + "1." + deadline.toString();
        assertEquals(expectedResult,
                tl.getTaskWithKeyword("task"));
    }
}
