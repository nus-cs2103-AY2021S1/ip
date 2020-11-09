package duke.core.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.core.task.Task;
import duke.core.task.ToDo;

class FindCommandTest {

    private final List<Task> taskList = new ArrayList<>(1);

    @Test
    void execute_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Populate taskList with 1 item
        taskList.add(new ToDo("Eat some chips"));

        // Execute the FindCommand
        new FindCommand(taskList, "t some c").execute();

        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator()
                + "1. [T][O] Eat some chips" + System.lineSeparator()
                + "Number of tasks found: 1" + System.lineSeparator();

        // Check output is same as expected
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(originalOut);
    }
}
