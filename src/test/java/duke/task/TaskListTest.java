package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void emptyTaskList_zeroSize() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getSize());
    }

    @Test
    void addTaskTest() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event event = new Event("iP Week 3", date);
        TaskList tasks = new TaskList();
        tasks.addTask(event);
        assertEquals(1, tasks.getSize());
        assertEquals("[E][✘] iP Week 3 (at: August 27 2020)", tasks.getTask(1).toString());
    }

    @Test
    void deleteTaskTest() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event event = new Event("iP Week 3", date);
        TaskList tasks = new TaskList();
        tasks.addTask(event);
        tasks.deleteTask(1);
        assertEquals(0, tasks.getSize());
    }

    @Test
    void showTasksOnDateTest() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Event event1 = new Event("iP Week 3", date);
        Event event2 = new Event("Reading", date);
        Event event3 = new Event("Finals", date);
        TaskList tasks = new TaskList();
        tasks.addTask(event1);
        tasks.addTask(event2);
        tasks.addTask(event3);
        assertEquals("\t 1.[E][✘] iP Week 3 (at: August 27 2020)\n" +
                "\t 2.[E][✘] Reading (at: August 27 2020)\n" +
                "\t 3.[E][✘] Finals (at: August 27 2020)\n", tasks.showTasksOnDate(date));
    }
}
