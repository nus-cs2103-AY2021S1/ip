package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String line = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_emptyList_success() {
        ListCommand command = new ListCommand();
        command.execute(new TaskList(), new Ui());
        String expected = line + "\t Here are the tasks in your list:\n\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void execute_nonEmptyList_success() {
        ListCommand command = new ListCommand();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("description");
        taskList.addTask(todo);
        command.execute(taskList, new Ui());
        String expected = line + "\t Here are the tasks in your list:\n"
                + "\t 1.[T][\u2718] description\n"
                + line;
        assertEquals(expected, outContent.toString());
    }
}
