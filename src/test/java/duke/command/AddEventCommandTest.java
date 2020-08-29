package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddEventCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecute() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddEventCommand addEventCommand = new AddEventCommand("eat", "22/02/1998 12:30");
        addEventCommand.execute(tasks, ui, storage);
        String expectedPrintStatement = "\t Got it. I've added this event: \n\t   "
                + "[E][✘] eat (at: 22 February 1998, 12:30 PM)\n\t "
                + "Now you have 1 task in the list.\n";
        assertEquals("[E][✘] eat (at: 22 February 1998, 12:30 PM)", tasks.getTask(0).toString());
        assertEquals(expectedPrintStatement, outContent.toString());
    }
}

