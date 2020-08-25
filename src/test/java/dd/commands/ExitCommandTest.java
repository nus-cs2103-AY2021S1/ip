package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void exitCommand_noFile() throws DukeException {
        String res = "You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________\n "
                + "Error writing to file.\n ";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new ExitCommand("exit", "").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
