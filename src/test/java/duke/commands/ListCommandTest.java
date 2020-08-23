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

public class ListCommandTest {

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
    public void execute_emptyList_success() {
        ListCommand command = new ListCommand();
        command.execute(new TaskList(), new Ui());
        String expected = LINE + "\t Here are the tasks in your list:\n\n" + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
    }

    @Test
    public void execute_nonEmptyList_success() {
        ListCommand command = new ListCommand();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("description");
        taskList.addTask(todo);
        command.execute(taskList, new Ui());
        String expected = LINE + "\t Here are the tasks in your list:\n"
                + "\t 1.[T][\u2718] description\n"
                + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
    }
}
