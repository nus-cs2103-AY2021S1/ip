import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.Todo;

public class StorageTest {
    @Test
    public void saveTasks_normalInput_savedCorrectly() {
        try {
            String path = "../data/duke.txt";
            Storage storage = new Storage(path);
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<Task> tasks = new ArrayList<>();

            tasks.add(new Todo("test"));
            storage.saveTasks(tasks);

            String input = bufferedReader.readLine();
            assertEquals(input, "T | 0 | test");
        } catch (DukeException e) {
            System.out.println("Error creating test case.");
        } catch (IOException e) {
            System.out.println("Error creating test case.");
        }
    }

    @Test
    public void getTasks_normalFile_getCorrectly() {
        try {
            String path = "../data/duke.txt";
            Storage storage = new Storage(path);
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write("T | 0 | test\n");
            fileWriter.close();

            List<Task> tasks = storage.getTasks();
            String task = tasks.get(0).toString();

            assertEquals(task, "[T][\u2718] test");
        } catch (DukeException e) {
            System.out.println("Error creating test case.");
        } catch (IOException e) {
            System.out.println("Error creating test case.");
        }
    }
}
