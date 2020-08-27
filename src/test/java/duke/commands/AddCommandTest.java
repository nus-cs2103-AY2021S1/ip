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

public class AddCommandTest {

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
    public void testExecute() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(todo);
        addCommand.execute(taskList, ui);
        assertEquals(line + "\t Got it. I've added this task:\n\t\t"
                + todo.toString()
                + "\n\t "
                + taskList.tasksRemaining()
                + "\n"
                + line, outContent.toString());
    }
}
