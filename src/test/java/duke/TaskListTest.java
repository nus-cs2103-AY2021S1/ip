package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.NoSuchTaskException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class TaskListTest {
    @Test
    public void get_correctInput_taskReturned() {
        List<Task> list = new ArrayList<>();
        ToDo toDo = new ToDo("watch TV");
        list.add(toDo);
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        try {
            assertEquals(tasks.get(0), toDo);
        } catch (NoSuchTaskException e) {
            fail();
        }
    }

    @Test
    public void get_wrongIndex_exceptionThrown() {
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("watch TV"));
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        try {
            assertEquals(tasks.get(10), null);
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals("Oh no! The index provided is not within the task list", e.toString());
        }
    }

    @Test
    public void size_correctInput_sizeReturned() {
        List<Task> list = new ArrayList<>();
        ToDo toDo = new ToDo("watch TV");
        list.add(toDo);
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        assertEquals(tasks.size(), 3);
    }

    @Test
    public void all_correctInput_allTasksReturned() {
        List<Task> list = new ArrayList<>();
        ToDo toDo = new ToDo("watch TV");
        list.add(toDo);
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        assertEquals(tasks.all(), list);
    }

    @Test
    public void remove_correctInput_removedTaskReturned() {
        List<Task> list = new ArrayList<>();
        ToDo toDo = new ToDo("watch TV");
        list.add(toDo);
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        try {
            assertEquals(tasks.remove(0), toDo);
            assertEquals(tasks.size(), 2);
        } catch (NoSuchTaskException e) {
            assertEquals("Oh no! The index provided is not within the task list", e.toString());
        }
    }

    @Test
    public void remove_wrongIndex_exceptionThrown() {
        List<Task> list = new ArrayList<>();
        ToDo toDo = new ToDo("watch TV");
        list.add(toDo);
        list.add(new Deadline("homework", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        list.add(new Event("event", LocalDateTime.parse("16/02/2019 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        TaskList tasks = new TaskList(list);
        try {
            assertEquals(tasks.remove(100), toDo);
            assertEquals(tasks.size(), 2);
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals("Oh no! The index provided is not within the task list", e.toString());
        }
    }
}
