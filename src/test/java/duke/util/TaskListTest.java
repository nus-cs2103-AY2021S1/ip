package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_validInput_storedCorrectly() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("test1");
        Deadline deadline = new Deadline("test2", LocalDate.now());
        Event event = new Event("test3", LocalDate.now());
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        List<Task> lst = new ArrayList<>(Arrays.asList(todo, deadline, event));
        assertIterableEquals(lst, tasks.getList());
    }

    @Test
    public void deleteTask_validInput_successfulDelete() {
        Todo todo = new Todo("test1");
        Deadline deadline = new Deadline("test2", LocalDate.now());
        Event event = new Event("test3", LocalDate.now());
        TaskList tasks = new TaskList(new ArrayList<>(Arrays.asList(todo, deadline, event)));
        tasks.deleteTask(1);
        List<Task> lst = new ArrayList<>(Arrays.asList(deadline, event));
        assertIterableEquals(lst, tasks.getList());
    }
}
