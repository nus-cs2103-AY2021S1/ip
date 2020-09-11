package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void storage_testLoad_expectedBehaviour() {
        Storage storage = new Storage("src/test/resources/storageTest.txt");
        try {
            List<Task> tasks = storage.load();
            assertEquals(2, tasks.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void storage_testLoad_exceptionThrown() {
        Storage storage = new Storage("src/test/resources/storageTestException.txt");
        assertThrows(DukeException.class, () -> storage.load());
    }
}
