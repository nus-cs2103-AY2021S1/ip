package duke;


import main.java.Duke.Commands.Command;
import main.java.Duke.Duke;
import main.java.Duke.Parser;
import main.java.Duke.Task.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void toDoTest() {
        Duke duke = new Duke();
        Parser parser = new Parser(duke);
        ArrayList<Task> arr = new ArrayList<>();
        ArrayList<Expense> expenses = new ArrayList<>();
        TaskList taskList = new TaskList(arr,expenses);
        Command cmd = parser.parse("todo Tutorial 1", taskList);
        cmd.execute();

        Task taskInserted = taskList.list.get(0);
        assertEquals(taskInserted.task, "Tutorial 1");
    }

    @Test
    public void deadlineTest() {
        Duke duke = new Duke();
        Parser parser = new Parser(duke);
        ArrayList<Task> arr = new ArrayList<>();
        ArrayList<Expense> expenses = new ArrayList<>();
        TaskList taskList = new TaskList(arr,expenses);
        Command cmd = parser.parse("deadline Tutorial 1 /by 2020-02-01", taskList);
        cmd.execute();

        Deadline deadlineInserted = (Deadline) taskList.list.get(0);
        assertEquals(deadlineInserted.deadline, LocalDate.parse("2020-02-01"));
    }
    @Test
    public void eventTest() {
        Duke duke = new Duke();
        Parser parser = new Parser(duke);
        ArrayList<Task> arr = new ArrayList<>();
        ArrayList<Expense> expenses = new ArrayList<>();
        TaskList taskList = new TaskList(arr,expenses);
        Command cmd = parser.parse("event Tutorial 1 /at 2020-02-01", taskList);
        cmd.execute();

        Event eventInserted = (Event) taskList.list.get(0);
        assertEquals(eventInserted.deadline, LocalDate.parse("2020-02-01"));
    }
}
