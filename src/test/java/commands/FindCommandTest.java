package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.commands.FindCommand;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommandTest {

    @Test
    public void testFindCommand() {
        AddCommand command1 = new AddCommand("todo", "laundry", null);
        AddCommand command2 = new AddCommand("todo", "do dishes", null);
        LocalDate date = LocalDate.parse("2020-08-23");
        LocalDate[] dateArr = {date};
        AddCommand command3 = new AddCommand("event", "movie with JH", dateArr);

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("/src/main/test/data.txt");
        command1.execute(tasks, ui, storage);
        command2.execute(tasks, ui, storage);
        command3.execute(tasks, ui, storage);

        FindCommand find = new FindCommand("movie");
        String response = find.execute(tasks, ui, storage);
        String expected = "[E][\u2718] movie with JH (at: 23 Aug 2020)\n";
        assertEquals(expected, response);
    }

}
