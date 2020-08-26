package moco.logic;

import main.java.moco.logic.TaskList;
import main.java.moco.task.Deadline;
import main.java.moco.task.Event;
import main.java.moco.task.Task;
import main.java.moco.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest1() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new Todo("Test task 1: Todo"));
        TaskList tasks = new TaskList(al);

        tasks.addTask(new Event("Test task 2: Event", LocalDate.parse("12-02-2020")));
        tasks.addTask(new Deadline("Test task 3: Deadline", LocalDate.parse("12-12-2020")));
        assertEquals(3, al.size());
    }

    @Test
    public void deleteTest1() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new Todo("Test task 1: Todo"));
        TaskList tasks = new TaskList(al);

        tasks.addTask(new Event("Test task 2: Event", LocalDate.parse("12-02-2020")));
        tasks.addTask(new Deadline("Test task 3: Deadline", LocalDate.parse("12-12-2020")));
        tasks.addTask(new Todo("Test task 4: Todo"));
        tasks.addTask(new Todo("Test task 5: Todo"));
        tasks.deleteTask(1);
        tasks.deleteTask(1);

        assertEquals(3, al.size());
    }

    @Test
    public void sizeTest1() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new Todo("Test task 1: Todo"));
        TaskList tasks = new TaskList(al);

        tasks.addTask(new Event("Test task 2: Event", LocalDate.parse("12-02-2020")));
        tasks.addTask(new Deadline("Test task 3: Deadline", LocalDate.parse("12-12-2020")));
        tasks.addTask(new Todo("Test task 4: Todo"));
        tasks.addTask(new Todo("Test task 5: Todo"));
        assertEquals(5, tasks.size());
    }
}