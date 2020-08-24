package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetrieveCommandTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void testExecute() throws DukeException {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("eat");
        Task task2 = new Deadline("sleep", "12/02/2012 12:12");
        Task task3 = new Event("play", "12/04/2014 12:14");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        Ui ui = new Ui();
        Storage storage = new Storage();
        RetrieveCommand retrieveCommand = new RetrieveCommand(LocalDate.parse("12/02/2012",
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        retrieveCommand.execute(tasks, ui, storage);
        String expectedPrintStatement = "\t Here are the deadlines and events happening on 12 February 2012:\n" +
                "\t 1.[D][✘] sleep (by: 12 February 2012, 12:12 PM) \n";
        assertEquals(expectedPrintStatement, OUT_CONTENT.toString());
    }
}

