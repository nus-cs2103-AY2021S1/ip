import duke.Storage;
import duke.Ui;
import duke.Parser;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void addDeadlineCommand_validInput_success() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("deadline play /by 1/4/2020 16:00").execute(tasks, ui, storage);
        String expected = "Success! This deadline task has been added: \n\t" +
                "[D][✗] play (by: Wed, Apr 01 2020, 04:00 PM)\n" +
                "You have 1 tasks in your list now.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void deleteTask_validInput_success() throws DukeException {
        Task todo = new Todo("read");
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(new ArrayList<>());
        todo.markAsDone();
        tasks.addTask(todo);
        Parser.parse("delete 1").execute(tasks, ui, storage);
        String expected = "Found it! This task has been successfully deleted: \n" +
                "\t[T][✓] read\n" + "You have 0 tasks in your list now.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void completeTask_invalidTaskID_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done 10").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! Invalid Task! The task does not exist. Input 'list' to view the correct task ID of your desired task.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void findTaskByDate_invalidDate_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find_by_date 10 May").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! The task date format is incorrect. Please input a valid date using the format: 'dd/mm/yyyy'";
        assertEquals(expected, ex.getMessage());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
