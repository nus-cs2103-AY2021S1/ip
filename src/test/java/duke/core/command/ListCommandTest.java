package duke.core.command;

import duke.core.task.Task;
import duke.core.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ListCommandTest {

    private final List<Task> taskList = new ArrayList<>(1);

    @Test
    void execute_outputExpected() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ensure something (anything) is printed
        taskList.add(new ToDo("Eat some chips"));
        new ListCommand(taskList).execute();
        assertNotEquals("", outContent.toString());

        System.setOut(originalOut);
    }

}
