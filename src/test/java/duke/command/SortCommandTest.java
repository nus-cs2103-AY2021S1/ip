package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;




public class SortCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task deadlineEarlier = new Deadline("sleep", "12/02/2012 12:12");
        Task todo1 = new Todo("eat");
        Task eventEarlier = new Event("play", "12/04/2014 12:14");
        Task todo2 = new Todo("dance");
        Task eventLater = new Event("drink", "12/12/2020 12:14");
        Task deadlineLater = new Deadline("sing", "12/12/2012 12:12");
        tasks.addTask(deadlineEarlier);
        tasks.addTask(todo1);
        tasks.addTask(eventEarlier);
        tasks.addTask(todo2);
        tasks.addTask(eventLater);
        tasks.addTask(deadlineLater);
        Ui ui = new Ui();
        Storage storage = new Storage();
        SortCommand sortCommand = new SortCommand();
        CommandResponse actual = sortCommand.execute(tasks, ui, storage);
        String expectedMessage = "Got it! I've sorted your tasks:\n"
                + "\t 1.[T][✘] eat\n"
                + "\t 2.[T][✘] dance\n"
                + "\t 3.[D][✘] sleep (by: 12 February 2012, 12:12 PM)\n"
                + "\t 4.[D][✘] sing (by: 12 December 2012, 12:12 PM)\n"
                + "\t 5.[E][✘] play (at: 12 April 2014, 12:14 PM)\n"
                + "\t 6.[E][✘] drink (at: 12 December 2020, 12:14 PM) ";
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected.getResponseMessage(), actual.getResponseMessage());
    }
}
