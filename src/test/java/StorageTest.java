import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StorageTest {
    @TempDir
    Path dataDir = Paths.get("data");
    final Path dataPath = Paths.get("data", "duke.txt");
    final Path aliasMapFilePath = Paths.get("data", "keymap.txt");

    @Test
    void loadTaskList() throws IOException, BlankTaskException {
        Storage storage = new Storage(dataPath, aliasMapFilePath);
        ArrayList<Task> tasks = new ArrayList<>(List.of(
                new ToDo("lorem"),
                new Deadline("ipsum", LocalDate.now(), LocalTime.NOON),
                new Event("Dolor", LocalDate.now(), LocalTime.NOON)));
        StringBuilder taskListString = new StringBuilder();
        for (Task task : tasks) {
            for (String attribute : task.attributeList()) {
                taskListString.append(attribute).append("\n");
            }
        }
        Files.writeString(dataPath, taskListString);

        try {
            assertEquals(storage.loadTaskList(), tasks);
        } catch (BlankTaskException | InvalidCommandException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void saveTaskList() throws BlankTaskException {

        //setup
        Storage storage = new Storage(dataPath, aliasMapFilePath);
        ArrayList<Task> tasks = new ArrayList<>(List.of(
                new ToDo("lorem"),
                new Deadline("ipsum", LocalDate.now(), LocalTime.NOON),
                new Event("Dolor", LocalDate.now(), LocalTime.NOON)));
        StringBuilder inputListString = new StringBuilder();
        for (Task task : tasks) {
            for (String attribute : task.attributeList()) {
                inputListString.append(attribute).append("\n");
            }
        }

        // saves tasks and compares output file
        Scanner data;
        try {
            storage.saveTaskList(tasks);
            data = new Scanner(dataPath);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
            return;
        }
        StringBuilder resultString = new StringBuilder();
        while (data.hasNextLine()) {
            resultString.append(data.nextLine()).append("\n");
        }
        assertEquals(inputListString.toString(), resultString.toString());

    }

}