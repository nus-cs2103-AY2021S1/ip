package sparrow.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void filterTasks() {
        Deadline d1 = new Deadline("I am a deadline", LocalDate.parse("2020-12-02"));
        Deadline d2 = new Deadline("I am another deadline", LocalDate.parse("2020-10-31"));
        Event e1 = new Event("I am an event", LocalDate.parse("2020-09-25"));

        TaskList tasks = new TaskList();
        tasks.addTask(d1);
        tasks.addTask(d2);
        tasks.addTask(e1);

        TaskList moreTasks = new TaskList();
        moreTasks.addTask(d2);
        moreTasks.addTask(e1);

        LocalDate dateFilter = LocalDate.parse("2020-10-31");
        assertEquals(tasks.filterTasks(dateFilter), moreTasks.filterTasks(dateFilter));
    }

    @Test
    public void findTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("I am a todo"));
        tasks.addTask(new Todo("I am a Todo"));
        tasks.addTask(new Deadline("I am not a Todo", LocalDate.now()));

        TaskList moreTasks = new TaskList();
        moreTasks.addTask(new Todo("I am a todo"));

        assertEquals(tasks.findTasks("todo"), moreTasks.findTasks("todo"));
    }
}
