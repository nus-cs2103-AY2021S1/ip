import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskTest {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

    @Test
    public void createTodo_validInput_success() {
        Todo todo = new Todo("create tests");
        String expected = "[T][✘] create tests";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void getTodoDescription_validDescription_success() {
        Todo todo = new Todo("Todo description");
        String expected = "Todo description";
        assertEquals(expected, todo.getDescription());
    }

    @Test
    public void createDeadline_validInput_success() {
        LocalDate date = LocalDate.parse("4/5/2020", this.dateFormatter);
        LocalTime time = LocalTime.parse("14:00", this.timeFormatter);
        Deadline deadline = new Deadline("create deadline", date, time);
        String expected = "[D][✘] create deadline (by: Mon, May 04 2020, 02:00 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void createEvent_validInput_success() {
        LocalDate date = LocalDate.parse("10/7/2020", this.dateFormatter);
        LocalTime time = LocalTime.parse("11:00", this.timeFormatter);
        Event event = new Event("create event", date, time);
        String expected = "[E][✘] create event (at: Fri, Jul 10 2020, 11:00 AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Create Junit Tests");
        todo.markAsDone();
        assertTrue(todo.hasBeenCompleted());
    }

    @Test
    public void setTaskTag_validTag_success() {
        Todo todo = new Todo("add todo");
        String tag = "fun";
        todo.setTaskTag(tag);
        assertEquals(tag, todo.getTaskTag());
    }

    @Test
    public void testGetTask() {
        Todo todo = new Todo("Junit tests");
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        tasks.addTask(todo);
        String expectedTask = "[T][✘] Junit tests";
        assertEquals(expectedTask, tasks.getTask(0).toString());
    }

    @Test
    public void testAddTask() {
        Todo todo = new Todo("attend lecture");
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        tasks.addTask(todo);
        assertEquals(1, tasks.getListSize());
    }

    @Test
    public void removeTask_eventTask_emptyTaskList() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        LocalDate date = LocalDate.parse("10/2/2020", this.dateFormatter);
        LocalTime time = LocalTime.parse("10:00", this.timeFormatter);
        Event event = new Event("Junit test", date, time);
        TaskList tasks = new TaskList(emptyListOfTasks);
        tasks.addTask(event);
        tasks.removeTask(0);
        assertEquals(0, tasks.getListSize());
    }

    @Test
    public void testCompleteTask() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        Todo todo = new Todo("create more tests");
        TaskList tasks = new TaskList(emptyListOfTasks);
        tasks.addTask(todo);
        tasks.completeTask(0);
        assertTrue(todo.hasBeenCompleted());
    }

    @Test
    public void convertTaskToFileString_eventTask_success() {
        LocalDate date = LocalDate.parse("1/10/2020", this.dateFormatter);
        LocalTime time = LocalTime.parse("19:00", this.timeFormatter);
        Event event = new Event("create junit tests", date, time);
        String expected = "E | 0 | create junit tests |  | 2020-10-01 19:00";
        assertEquals(expected, event.convertTaskToFileString());
    }

    @Test
    public void convertTaskToFileString_todoTask_success() {
        Todo todo = new Todo("attend tutorials");
        String expected = "T | 0 | attend tutorials | ";
        assertEquals(expected, todo.convertTaskToFileString());
    }
}
