import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import stubs.ToDoStub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadFileFromMemory_todoStub_success() throws DukeException, IOException {
        // clear file from previous session if it exists
        try {
            Files.delete(Paths.get(Duke.FILE_PATH));
           
        } catch (NoSuchFileException exception) {
            System.out.println("File path does not originally exist");
        } finally {
            Storage store = new Storage(Duke.FILE_PATH);
            List<Task> expected = new ArrayList<Task>();
            expected.add(new ToDoStub("test description"));
            store.saveTaskToMemory(expected);
            store.saveTaskToMemory(expected);
            List<Task> actual = store.loadTasksFromMemory();
            if (expected.size() == actual.size()) {
                for (int i = 0; i < expected.size(); i++) {
                    // compare if expected equals to Task retrieved from memory
                    assertEquals(true, expected.get(i).equals(actual.get(i)));
                }
            }
        }
    }

    @Test
    public void testLoadFileFromMemory_pathDoesNotExist_taskListReturned() throws IOException, DukeException {
        // if file path exist during test, delete it
        try {
            Files.delete(Paths.get(Duke.FILE_PATH));
        } catch (NoSuchFileException exception) {
        } finally {
            Storage store = new Storage(Duke.FILE_PATH);
            List<Task> expected = new ArrayList<>();
            assertEquals(expected, store.loadTasksFromMemory());
        }
    }

}