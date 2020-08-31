package king;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void loadTest() {
        Storage testStorage = new Storage("data/test/storage/loadTest.txt");
        TaskList taskList = new TaskList();
        taskList.addAll(testStorage.load());
        assertEquals(7, taskList.size());
    }

    @Test
    public void persistTaskList_invalidFilePath_false() {
        Storage testStorage = new Storage("data/test/storage/invalidStorage.txt");
        boolean actual = testStorage.persistTaskList(null);
        assertEquals(false, actual);
    }
}
