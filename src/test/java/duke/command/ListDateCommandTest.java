package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListDateCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void execute_validDate_printsCorrectTasks() throws DukeException {
        TaskList tasks = new TaskList();
        String dateString = "2019-02-03";
        tasks.add(new Deadline("sing", LocalDate.parse(dateString)));
        tasks.add(new Deadline("shower", LocalDate.parse("2012-02-02")));
        tasks.add(new Deadline("sleep", LocalDate.parse("2019-02-04")));
        tasks.add(new Deadline("talk", LocalDate.parse(dateString)));
        tasks.add(new Deadline("walk", LocalDate.parse(dateString)));
        new ListDateCommand("date " + dateString).execute(tasks);
        assertEquals("1. [D][ ] sing (by: Feb 3 2019)\n" +
                "2. [D][ ] talk (by: Feb 3 2019)\n" +
                "3. [D][ ] walk (by: Feb 3 2019)\n" +
                "\n", outContent.toString());
    }
}