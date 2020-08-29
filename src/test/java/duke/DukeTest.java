package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DukeTest {


    private TaskList taskList = new TaskList();
    private Task todo = new Todo("test1");

    private LocalDate today = LocalDate.parse("2020-08-24");
    private LocalDate tmr = LocalDate.parse("2020-08-25");
    private LocalTime startTime = LocalTime.parse("12:00");
    private LocalTime endTime = LocalTime.parse("14:00");
    private LocalTime time = LocalTime.parse("09:00");
    private Task event = new Event("test2", today, startTime, endTime);

    private Task deadline = new Deadline("test3", tmr, time);

    private TaskList createTaskList(Task task) {
        List<Task> list = new ArrayList<>();
        list.add(task);
        return new TaskList(list);
    }

    @Test
    public void todoTest() {
        taskList = createTaskList(todo);
        assertEquals(todo.toString(), taskList.getTask(1).toString());
    }

    @Test
    public void eventTest() {
        taskList = createTaskList(event);
        assertEquals(event.toString(), taskList.getTask(1).toString());
    }

    @Test
    public void deadlineTest() {
        taskList = createTaskList(deadline);
        assertEquals(deadline.toString(), taskList.getTask(1).toString());
    }

}

