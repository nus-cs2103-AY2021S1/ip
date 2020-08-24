import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void executeTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ByeCommand byeCommand = new ByeCommand();

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));

        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        String currentDir = System.getProperty("user.dir");
        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";

        Storage storage = new Storage(path);

        byeCommand.execute(taskList, ui, storage);

        String expectedOutput = "    Bye. Hope to see you again soon!\n"; // Notice the \n for new line.

        assertEquals(expectedOutput, outContent.toString());
    }
}