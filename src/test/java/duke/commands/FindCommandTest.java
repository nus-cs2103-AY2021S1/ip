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

public class FindCommandTest {

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
    public void execute_noMatch_showNoMatchFound() {
        FindCommand command = new FindCommand("word");
        command.execute(new TaskList(), new Ui());
        String expected = line + "\t There are no tasks that matches your search word.\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void execute_hasMatch_showMatchedTasks() {
        FindCommand command = new FindCommand("word");
        TaskList taskList = new TaskList();
        Todo matched = new Todo("has word");
        Todo unmatched = new Todo("no");
        taskList.addTask(matched);
        taskList.addTask(unmatched);
        command.execute(taskList, new Ui());
        String expected = line
                + "\t Here are the matching tasks in your list:\n"
                + "\t 1."
                + matched.toString()
                + "\n" + line;
        assertEquals(expected, outContent.toString());
    }

}
