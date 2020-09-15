package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommandTest {

    @Test
    public void testNewTodo() {
        AddCommand command = new AddCommand("todo", "laundry", null);
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("/src/main/test/data.txt");
        String response = command.execute(tasks, ui, storage);
        String expected = "Got it.(^∇^) I've added this task:\n[T][\u2718] laundry";
        assertEquals(expected, response);
    }

    @Test
    public void testNewDeadline() {
        LocalDate date = LocalDate.parse("2020-08-23");
        LocalDate[] dateArr = {date};
        AddCommand command = new AddCommand("deadline", "submit draft 1", dateArr);
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("/src/main/test/data.txt");
        String response = command.execute(tasks, ui, storage);
        String expected = "Got it.(^∇^) I've added this task:\n[D][\u2718] submit draft 1 (by: 23 Aug 2020)";
        assertEquals(expected, response);
    }

}
