package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.exception.*;
import duke.task.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {


    TaskList taskList = new TaskList();
    Task todo = new Todo("test1");

    LocalDate today = LocalDate.parse("2020-08-24");
    LocalDate tmr = LocalDate.parse("2020-08-25");
    LocalTime startTime = LocalTime.parse("12:00");
    LocalTime endTime = LocalTime.parse("14:00");
    LocalTime time = LocalTime.parse("09:00");
    Task event = new Event("test2", today, startTime, endTime);

    Task deadline = new Deadline("test3", tmr, time);

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

