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

public class TestAddCommnad {

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
    public void testExecute() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(todo);
        addCommand.execute(taskList, ui);
        assertEquals(LINE + "\t Got it. I've added this task:\n\t\t"
                + todo.toString()
                + "\n\t "
                + taskList.tasksRemaining()
                + "\n"
                + LINE, OUT_CONTENT.toString());
    }
}
