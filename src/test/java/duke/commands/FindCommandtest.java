package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandtest {

    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final String LINE = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void execute_noMatch_showNoMatchFound() {
        FindCommand command = new FindCommand("word");
        command.execute(new TaskList(), new Ui());
        String expected = LINE + "\t There are no tasks that matches your search word.\n" + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
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
        String expected = LINE
                + "\t Here are the matching tasks in your list:\n"
                + "\t 1."
                + matched.toString()
                + "\n" + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
    }

}
