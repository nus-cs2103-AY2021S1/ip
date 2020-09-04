package king;

import storage.Storage;
import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void loadTest() throws KingException{
        Storage testStorage = new Storage("data/test/storage/loadTest.txt");
        TaskList taskList = new TaskList();
        taskList.addAll(testStorage.load());
        assertEquals(7, taskList.size());
    }

    @Test
    public void persistTaskList_invalidFilePath_false() throws KingException{
        Storage testStorage = new Storage("data/test/storage/invalidStorage.txt");
        boolean actual = testStorage.persistTaskList(null);
        assertEquals(false, actual);
    }
}
