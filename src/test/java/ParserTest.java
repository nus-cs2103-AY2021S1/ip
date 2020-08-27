import main.java.Parser;
import main.java.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    @Test
    public void discontinueConversation() {
        TaskList taskList = new TaskList();
        boolean result = Parser.understandText("bye", taskList);
        assertFalse(result);

    }
    @Test
    public void addTask() {
        try {
            File savedFile = new File("./data/save.txt");
            if (savedFile.exists()) {
                FileWriter writer = new FileWriter("./data/save.txt", false);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskList taskList = new TaskList();
        boolean result = Parser.understandText("todo sample task", taskList);
        assertEquals(1, taskList.getAllTasks().size());
    }
}