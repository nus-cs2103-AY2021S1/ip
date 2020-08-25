package king;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    Storage testStorage = new Storage("data/test/storageTest.txt");

    @Test
    public void loadTest(){
        TaskList taskList = new TaskList();
        taskList.addAll(testStorage.load());
        assertEquals(2,taskList.size());
    }

    @Test
    public void persistTaskList_invalidFilePath_false(){
        boolean actual = testStorage.persistTaskList(null);
        assertEquals(false,actual);
    }
}
